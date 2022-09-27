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
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.analyse.api.feign.RemoteDataService;
import com.pig4cloud.pig.analyse.entity.DangerLevel;
import com.pig4cloud.pig.analyse.entity.Summary;
import com.pig4cloud.pig.analyse.mapper.SummaryMapper;
import com.pig4cloud.pig.analyse.service.DangerLevelService;
import com.pig4cloud.pig.analyse.service.SummaryService;
import com.pig4cloud.pig.analyse.utils.AnalyseUtil;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.datas.dto.DataChinaCityDto;
import com.pig4cloud.pig.datas.dto.ProvinceDto;
import com.pig4cloud.pig.datas.entity.DataChinaCity;
import com.pig4cloud.pig.datas.entity.DataInOutNum;
import com.pig4cloud.pig.datas.vo.ProvinceVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 分析总结
 *
 * @author fanglong
 * @date 2022-07-06 08:01:09
 */
@Service
@RequiredArgsConstructor
public class SummaryServiceImpl extends ServiceImpl<SummaryMapper, Summary> implements SummaryService {
	private final RemoteDataService remoteDataService;
	private final DangerLevelService dangerLevelService;
	@Override
	public List<Summary> buildSummary(String date) {

		List<Summary> list = new ArrayList<>();
		String part1 = buildPart1(date);
		list.add(new Summary(Integer.parseInt(date.replace("-", "") + "1"), "1", part1, date));
		String part2 = buildPart2(date);
		list.add(new Summary(Integer.parseInt(date.replace("-", "") + '2'), "2", part2, date));
		String part3 = buildPart3(date);
		list.add(new Summary(Integer.parseInt(date.replace("-", "") + '3'), "3", part3, date));
		String part4 = buildPart4(date);
		list.add(new Summary(Integer.parseInt(date.replace("-", "") + '4'), "4", part4, date));
		return list;
	}


	private List<DataChinaCity> getRecentConfirm(String date) {
		DataChinaCityDto chinaCityDto = new DataChinaCityDto();
		chinaCityDto.setPro("福建");
		chinaCityDto.setDate(date);
		R<List<DataChinaCity>> listR = remoteDataService.queryRecent(chinaCityDto, SecurityConstants.FROM_IN);
		return listR.getData();
	}

	private HashMap judgeRecentTrend(List<DataChinaCity> list) {
		HashMap<String, String> resultMap = new HashMap<>();
		HashMap<String, Long> tempMap = new HashMap<>();
		tempMap.put("莆田", 0L);
		tempMap.put("厦门", 0L);
		tempMap.put("漳州", 0L);
		tempMap.put("泉州", 0L);
		tempMap.put("福州", 0L);
		tempMap.put("三明", 0L);
		tempMap.put("南平", 0L);
		tempMap.put("龙岩", 0L);
		tempMap.put("宁德", 0L);
		for (DataChinaCity chinaCity : list) {
			Long val;
			switch (chinaCity.getCity()) {
				case ("莆田"):
					val = tempMap.get("莆田");
					tempMap.replace("莆田", val, val + chinaCity.getConfirmedRelative());
					break;
				case ("厦门"):
					val = tempMap.get("厦门");
					tempMap.replace("厦门", val, val + chinaCity.getConfirmedRelative());
					break;
				case ("漳州"):
					val = tempMap.get("漳州");
					tempMap.replace("漳州", val, val + chinaCity.getConfirmedRelative());
					break;
				case ("泉州"):
					val = tempMap.get("泉州");
					tempMap.replace("泉州", val, val + chinaCity.getConfirmedRelative());
					break;
				case ("福州"):
					val = tempMap.get("福州");
					tempMap.replace("福州", val, val + chinaCity.getConfirmedRelative());
					break;
				case ("三明"):
					val = tempMap.get("三明");
					tempMap.replace("三明", val, val + chinaCity.getConfirmedRelative());
					break;
				case ("南平"):
					val = tempMap.get("南平");
					tempMap.replace("南平", val, val + chinaCity.getConfirmedRelative());
					break;
				case ("龙岩"):
					val = tempMap.get("龙岩");
					tempMap.replace("龙岩", val, val + chinaCity.getConfirmedRelative());
					break;
				case ("宁德"):
					val = tempMap.get("宁德");
					tempMap.replace("宁德", val, val + chinaCity.getConfirmedRelative());
					break;
				default:
					break;
			}
		}
		//key 是城市名
		tempMap.forEach((key, value) -> {
			//if (value <= 0){
			//resultMap.put(key,"疫情目前稳定");
			//}
			if (value > 0 && value < 20) {
				resultMap.put(key, "疫情初露苗头");
			} else if (value > 20) {
				resultMap.put(key, "疫情还是严重");
			}
		});
		return resultMap;
	}

	private List<DataInOutNum> getAttentionCities(String date, HashMap citiesMap) {
		List<DataInOutNum> attentionCities = new ArrayList<>();
		DataInOutNum dataInOutNum = new DataInOutNum();
		dataInOutNum.setType("out");
		dataInOutNum.setValue(BigDecimal.valueOf(20));
		dataInOutNum.setDate(date.replace("-", ""));
		citiesMap.forEach((key, value) -> {
			dataInOutNum.setTargetCity((String) key);
			R<List<DataInOutNum>> listR = remoteDataService.queryAttentionCities(dataInOutNum, SecurityConstants.FROM_IN);
			//TODO 没做是否返回成功的判断。
			attentionCities.addAll(listR.getData());
		});
		return attentionCities;
	}

	private String buildPart1(String date) {
		List<DataChinaCity> recentConfirm = getRecentConfirm(date);
		HashMap hashMap = judgeRecentTrend(recentConfirm);
//        StringBuilder steadyCities = new StringBuilder();
		StringBuilder beginCities = new StringBuilder();
		StringBuilder seriousCities = new StringBuilder();

		hashMap.forEach((key, value) -> {
//            if (value.equals("疫情目前稳定")){
//                steadyCities.append(key).append('、');
//            }
//            else
			if (value.equals("疫情初露苗头")) {
				beginCities.append(key).append('、');
			} else if (value.equals("疫情还是严重")) {
				seriousCities.append(key).append('、');
			}
		});
		String str1 = "就过去三天确诊情况来看，";
		if (!StrUtil.hasEmpty(beginCities.toString())) {
			str1 = str1 + beginCities.toString() + "疫情初露苗头;";
		}
		if (!StrUtil.hasEmpty(seriousCities.toString())) {
			str1 = str1 + seriousCities.toString() + "疫情还是严重。";
		}


		StringBuilder attentionCities = new StringBuilder();
		//获取需要格外注意的地区
		List<DataInOutNum> attentionCityList = getAttentionCities(date, hashMap);
		for (DataInOutNum inOutNum : attentionCityList) {
			attentionCities.append(inOutNum.getCityName()).append('、');
		}
		String str2;
		if (CharSequenceUtil.hasEmpty(attentionCities.toString())) {
			str2 = "结合迁徙大数据，应注意周边地区的防控工作。";
		} else {
			str2 = "结合迁徙大数据，应注意" + attentionCities.toString() + "地区的防控工作。";
		}
		return str1 + str2;
	}

	private String buildPart2(String date) {
		//配置查询条件
		DataChinaCityDto dataChinaCityDto = new DataChinaCityDto();
		dataChinaCityDto.setDate(date);
		dataChinaCityDto.setPro("福建");
		dataChinaCityDto.setCurConfirm(20L);
		//调用feign
		R<List<DataChinaCity>> listR = remoteDataService.queryLocatedCities(dataChinaCityDto, SecurityConstants.FROM_IN);
		StringBuilder citiesStr = new StringBuilder();
		for (DataChinaCity chinaCity : listR.getData()) {
			citiesStr.append(chinaCity.getCity()).append('、');
		}
		return "目前疫情主要分布在：" + citiesStr.toString() + "地区，应注意此范围的疫情防控。";
	}

	private String buildPart3(String date) {
		DangerLevel dangerLevel = dangerLevelService.getOne(Wrappers.<DangerLevel>lambdaQuery()
				.likeRight(DangerLevel::getCreateTime, LocalDateTimeUtil.parse(date, "yyyy-MM-dd")));
		if(!ObjectUtil.isNull(dangerLevel)){
			//Field[] fields = ReflectUtil.getFields(DangerLevel.class);//获取类的字段,这里本来想跟js一样，循环键，根据键获取值，再判断。还得学啊年轻人
			StringBuilder citiesStr = new StringBuilder();
			BigDecimal val;
			for (int i = 0; i < 8; i++) {
				switch (i) {
					case (0):
						val = dangerLevel.getFuzhou();
						if (val.compareTo(new BigDecimal(1250)) > 0) {
							citiesStr.append("福州、");
						}
						break;
					case (1):
						val = dangerLevel.getXiamen();
						if (val.compareTo(new BigDecimal(1250)) > 0) {
							citiesStr.append("厦门、");
						}
						break;
					case (2):
						val = dangerLevel.getNingde();
						if (val.compareTo(new BigDecimal(1250)) > 0) {
							citiesStr.append("宁德、");
						}
						break;
					case (3):
						val = dangerLevel.getNanping();
						if (val.compareTo(new BigDecimal(1250)) > 0) {
							citiesStr.append("南平、");
						}
						break;
					case (4):
						val = dangerLevel.getSanming();
						if (val.compareTo(new BigDecimal(1250)) > 0) {
							citiesStr.append("三明、");
						}
						break;
					case (5):
						val = dangerLevel.getQuanzhou();
						if (val.compareTo(new BigDecimal(1250)) > 0) {
							citiesStr.append("泉州、");
						}
						break;
					case (6):
						val = dangerLevel.getZhangzhou();
						if (val.compareTo(new BigDecimal(1250)) > 0) {
							citiesStr.append("漳州、");
						}
						break;
					case (7):
						val = dangerLevel.getLongyan();
						if (val.compareTo(new BigDecimal(1250)) > 0) {
							citiesStr.append("龙岩、");
						}
						break;
					case (8):
						val = dangerLevel.getPutian();
						if (val.compareTo(new BigDecimal(1250)) > 0) {
							citiesStr.append("莆田、");
						}
						break;
					default:
						break;
				}
			}
			return "就危险系数来看，" + citiesStr.toString() + "发出了不同程度的预警，应当及时采取防范措施。";
		}
		else {
			log.warn("由于缺少危险等级数据，第三部分总结无法生成！");
			return "由于缺少危险等级数据，第三部分总结无法生成！";
		}

	}

	private String buildPart4(String date) {
		String shortTrendStr;
		//配置查询条件
		ProvinceDto provinceDto = new ProvinceDto();
		provinceDto.setStartDate(AnalyseUtil.getPlusDate(date,-3));
		provinceDto.setEndDate(date);
		R<ProvinceVo> provinceVoR = remoteDataService.queryTrend(provinceDto, SecurityConstants.FROM_IN);
		//提取结果 TODO 没做返回情况判断。
		ProvinceVo provinceVo = provinceVoR.getData();
		Long confirmedRelativeSum = provinceVo.getConfirmedRelativeSum();
		//做判断
		if (confirmedRelativeSum > 0) {
			shortTrendStr = "就近期全省总体数据来看，疫情形式日渐严峻，";
		} else if (confirmedRelativeSum == 0) {
			shortTrendStr = "就近期全省总体数据来看，疫情形式较为稳定，";
		} else {
			shortTrendStr = "就近期全省总体数据来看，疫情形式有转好迹象，";
		}
		//配置条件
		provinceDto.setStartDate(AnalyseUtil.getPlusDate(date,-14));
		R<ProvinceVo> provinceVoR2 = remoteDataService.queryTrend(provinceDto, SecurityConstants.FROM_IN);
		Long confirmedRelativeSum2 = provinceVoR2.getData().getConfirmedRelativeSum();
		String longTrendStr = "";
		if (confirmedRelativeSum > 0 && confirmedRelativeSum2 > 0) {
			longTrendStr = "望各部门各单位齐心协力，攻克难关。";
		} else if (confirmedRelativeSum > 0 && confirmedRelativeSum2 <= 0) {
			longTrendStr = "一波未平一波又起，望各部门之间相互配合，共度难关。";
		} else if (confirmedRelativeSum <= 0 && confirmedRelativeSum2 > 0) {
			longTrendStr = "但是疫情的走向仍扑朔迷离，各单位万不可掉以轻心。";
		} else if (confirmedRelativeSum <= 0 && confirmedRelativeSum2 <= 0) {
			longTrendStr = "疫情趋于稳定，各地区可适度开放。";
		}
		return shortTrendStr + longTrendStr;
	}
}
