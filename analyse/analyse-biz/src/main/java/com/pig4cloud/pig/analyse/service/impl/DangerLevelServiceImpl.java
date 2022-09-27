/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */
package com.pig4cloud.pig.analyse.service.impl;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.analyse.api.feign.RemoteDataService;
import com.pig4cloud.pig.analyse.service.DangerLevelService;
import com.pig4cloud.pig.analyse.entity.DangerLevel;
import com.pig4cloud.pig.analyse.mapper.DangerLevelMapper;
import com.pig4cloud.pig.analyse.utils.AnalyseUtil;
import com.pig4cloud.pig.analyse.utils.CityId;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.datas.entity.DataChinaCity;
import com.pig4cloud.pig.datas.entity.DataInOutNum;
import com.pig4cloud.pig.datas.utils.DatesUtil;
import com.pig4cloud.pig.datas.entity.DataInOutNumCurve;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * 危险等级
 *
 * @author fanglong
 * @date 2022-07-06 07:59:51
 */
@Service
@RequiredArgsConstructor
public class DangerLevelServiceImpl extends ServiceImpl<DangerLevelMapper, DangerLevel> implements DangerLevelService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DangerLevelServiceImpl.class);

	private final RemoteDataService remoteDataService;

    @Override
    public DangerLevel calculate(String newDate) {
        HashMap<String, BigDecimal> map = new HashMap<>();
        //查询福建省每个城市昨天的迁入城市、和每个城市的迁入指数
        for (CityId item : CityId.values()) {
            String cityName = item.getCityName();
            //获取迁入城市
            List<DataInOutNum> moveInCities = getMoveInCities(cityName, DatesUtil.getPlusDate(newDate, -1));
            //获取迁入指数
            DataInOutNumCurve curve = getMoveInNum(cityName, DatesUtil.getPlusDate(newDate, -1));
            BigDecimal tempResult = new BigDecimal(0);
            //查询这些迁入城市的最新疫情数据
            for (DataInOutNum moveInCity : moveInCities) {
				//todo 感觉关于时间的条件总有点问题
                DataChinaCity cityInfo = getCityInfo(moveInCity.getCityName(), DatesUtil.getPlusDate(newDate, -1), 0);
                tempResult = getDangerLevel(curve, tempResult, moveInCity, cityInfo);
            }
            map.put(cityName, tempResult);
        }

        DangerLevel dangerLevel = new DangerLevel(map.get("福州"), map.get("厦门"), map.get("莆田")
                , map.get("三明"), map.get("泉州"), map.get("漳州")
                , map.get("南平"), map.get("龙岩"), map.get("宁德")
        );
        dangerLevel.setCreateTime(LocalDateTimeUtil.parse(newDate,"yyyy-MM-dd"));
        return dangerLevel;
    }

	private List<DataInOutNum> getMoveInCities(String targetCityName, String date) {
		DataInOutNum dataInOutNum = new DataInOutNum();
		dataInOutNum.setType("in");
		dataInOutNum.setTargetCity(targetCityName);
		dataInOutNum.setDate(StringUtils.replace(date,"-",""));
		R<List<DataInOutNum>> listR = remoteDataService.queryDataInOutNum(dataInOutNum, SecurityConstants.FROM_IN);
		if (listR.getCode() == 200){
			List<DataInOutNum> list = listR.getData();
			//如果没查到这天的数据就查前一天的，直到查到为止。
			if (list.isEmpty()){
				list = getMoveInCities(targetCityName, DatesUtil.getPlusDate(date,-1));
			}
			return list;
		}
		//TODO 这里如果code不是200的话应该抛出一个异常的
		return listR.getData();
	}

	private BigDecimal getDangerLevel(DataInOutNumCurve curve, BigDecimal tempResult, DataInOutNum moveInCity, DataChinaCity cityInfo) {
        if (ObjectUtil.isNotNull(cityInfo)) {
            //TODO 这里获取AsymptomaticRelative会出现值为“”的情况，这里是直接从数据库改换成0，暂时！！
            BigDecimal asy = new BigDecimal(cityInfo.getAsymptomaticRelative());//新增无症状
            BigDecimal curConfirm = new BigDecimal(cityInfo.getCurConfirm());//现有确诊
            BigDecimal add = asy.add(curConfirm);
            tempResult = tempResult.add(curve.getValue().multiply(moveInCity.getValue()).multiply(add));
//            tempResult = tempResult.add(curve.getValue().multiply(moveInCity.getValue()).multiply(curConfirm));
//            curve.getValue() * moveInCity.getValue() * (city.getAsymptomatic() + city.getCurConfirm())
        }
        //TODO 这个返回值还需仔细斟酌
        return tempResult;
    }


	private DataInOutNumCurve getMoveInNum(String cityName, String date){
		DataInOutNumCurve dataInOutNumCurve = new DataInOutNumCurve(cityName,"in",StringUtils.replace(date,"-",""));
		R<DataInOutNumCurve> dataInOutNumCurveR = remoteDataService.queryDataInOutNumCurve(dataInOutNumCurve,SecurityConstants.FROM_IN);
		DataInOutNumCurve curve = dataInOutNumCurveR.getData();
		if (dataInOutNumCurveR.getCode() != 200){
			//如果没有查到就再查一遍
			curve = getMoveInNum(cityName,AnalyseUtil.getPlusDate(date,-1));
		}
		return curve;
	}


	private DataChinaCity getCityInfo(String cityName, String date,int retryTimes){
		if (cityName.contains("张家")){
			cityName = cityName.substring(0,3);
		}else {
			cityName = cityName.substring(0,2);
		}
		//根据城市名和日期查询的，正常应该只有一条数据
		DataChinaCity dataChinaCity = new DataChinaCity(cityName,date);
		R<List<DataChinaCity>> listR = remoteDataService.queryDataChinaCity(dataChinaCity, SecurityConstants.FROM_IN);
		if (listR.getCode() == 200){
			if (listR.getData().size()>1){
				LOGGER.warn("危险等级计算数据有误：查询到的数据应当为一条，实际有" + listR.getData().size() + "条！");
			}
			DataChinaCity chinaCity = listR.getData().get(0);
			if (ObjectUtil.isNull(chinaCity)){
				if (retryTimes < 5){
					chinaCity = getCityInfo(cityName, AnalyseUtil.getPlusDate(date, -1),retryTimes+1);
				}else {
					LOGGER.warn(cityName + "=》 疫情数据查询异常");
					return null;
				}
			}
			return chinaCity;
		}
		else {
			LOGGER.error("feign访问异常！");
			return null;
		}
	}
}
