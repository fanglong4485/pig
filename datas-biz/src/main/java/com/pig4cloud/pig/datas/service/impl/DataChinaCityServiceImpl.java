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
package com.pig4cloud.pig.datas.service.impl;

import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.datas.Utils.DatasUtils;
import com.pig4cloud.pig.datas.Vo.CityInfo;
import com.pig4cloud.pig.datas.Vo.RadarVo;
import com.pig4cloud.pig.datas.Vo.Ranking;
import com.pig4cloud.pig.datas.entity.DataChinaCity;
import com.pig4cloud.pig.datas.mapper.DataChinaCityMapper;
import com.pig4cloud.pig.datas.service.DataChinaCityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 疫情数据表
 *
 * @author fanglong
 * @date 2022-07-01 02:57:58
 */
@Service
@AllArgsConstructor
public class DataChinaCityServiceImpl extends ServiceImpl<DataChinaCityMapper, DataChinaCity> implements DataChinaCityService {

    private final DataChinaCityMapper dataChinaCityMapper;

    /**
     * 生成福建省数据概览
     * @param date 日期
     * @return 概览对象集合
     */
    @Override
    public List<Map<String, Object>> getOverview(String date) {
        //根据时间查询疫情数据，如果日期参数为空，则是当前时间
        QueryWrapper<DataChinaCity> wrapper = new QueryWrapper<>();
        wrapper
                //新增确诊
                .select("IFNULL(SUM(confirmed_relative),0) as confirmedAdd"
                        //新增本土
                ,"IFNULL(SUM(native_relative),0) as nativeAdd"
                        //新增无症状
                ,"IFNULL(SUM(asymptomatic_relative),0) as asymptomaticAdd"
                        //现有确诊
                ,"IFNULL(SUM(cur_confirm),0) as curConfirm"
                        //累计确诊
                ,"IFNULL(SUM(confirmed),0) as confirmed"
                        //累计治愈
                ,"IFNULL(SUM(crued),0) as crued"
                        //累计死亡
                ,"IFNULL(SUM(died),0) as died")
                .eq("pro","福建");
        if (CharSequenceUtil.hasEmpty(date)){
            wrapper.likeRight("create_time", DatasUtils.getDate(new Date()));
        } else {
            wrapper.likeRight("create_time",date);
        }

        return dataChinaCityMapper.selectMaps(wrapper);

    }

    /**
     * 生成柱状图
     * @param date 日期
     * @return 柱状图视图对象
     */
    @Override
    public CityInfo getCityInfo(String date) {
        //查询条件：1.日期 2.福建省
        LambdaQueryWrapper<DataChinaCity> wrapper = new QueryWrapper<DataChinaCity>().lambda();
        wrapper.select(DataChinaCity::getCity,DataChinaCity::getConfirmedRelative,DataChinaCity::getCrued)
                .eq(DataChinaCity::getPro,"福建")
                .likeRight(DataChinaCity::getCreateTime,date)
                .orderByAsc(DataChinaCity::getCityCode);
        List<DataChinaCity> cityList = dataChinaCityMapper.selectList(wrapper);

        CityInfo cityVo = new CityInfo();
        ArrayList<String> areaList = new ArrayList<>();
        ArrayList<Long> confirmedList = new ArrayList<>();
        ArrayList<Long> confirmedRelativeList = new ArrayList<>();
        ArrayList<Long> cruedList = new ArrayList<>();
        for (DataChinaCity city : cityList) {
            areaList.add(city.getCity());
            confirmedList.add(city.getConfirmed());
            confirmedRelativeList.add(city.getConfirmedRelative());
            cruedList.add(city.getCrued());
        }
        cityVo.setCategory(areaList);
        cityVo.setLineData(confirmedList);
        cityVo.setBarData(confirmedRelativeList);
        cityVo.setRateData(cruedList);
        return cityVo;
    }

    /**
     * 生成排名信息
     * @param date 日期
     * @return 排名视图对象
     */
    @Override
    public List<?> getRankingInfo(String date) {
        ArrayList<Ranking> results = new ArrayList<>();
        List<DataChinaCity> cities = dataChinaCityMapper.selectList(Wrappers.<DataChinaCity>lambdaQuery()
                .select(DataChinaCity::getCity, DataChinaCity::getConfirmedRelative)
                .eq(DataChinaCity::getPro, "福建")
                .likeRight(DataChinaCity::getCreateTime, date));
        for (DataChinaCity city : cities) {
            results.add(new Ranking(city.getCity(), city.getConfirmedRelative()));
        }

        return results;
    }

    /**
     * 查询雷达图所需数据
     * @param date 日期
     * @return 雷达视图对象
     */
    @Override
    public RadarVo getRadarData(String date) {
        //查今天全省的数据
        List<Map<String, Object>> todayList = dataChinaCityMapper.selectMaps(Wrappers.<DataChinaCity>query()
                        //新增确诊
                .select("IFNULL(SUM(confirmed_relative),0) as confirmedAdd"
                        //新增本土
                        ,"IFNULL(SUM(native_relative),0) as nativeAdd"
                        //新增无症状
                        ,"IFNULL(SUM(asymptomatic_relative),0) as asymptomaticAdd"
                        //现有确诊
                        ,"IFNULL(SUM(cur_confirm),0) as curConfirm"
                ).eq("pro","福建")
                .likeRight("create_time",date)
                );

        //查询昨天全省的数据
        List<Map<String, Object>> yesterdayList = dataChinaCityMapper.selectMaps(Wrappers.<DataChinaCity>query()
                        //新增确诊
                .select("IFNULL(SUM(confirmed_relative),0) as confirmedAdd"
                        //新增本土
                        ,"IFNULL(SUM(native_relative),0) as nativeAdd"
                        //新增无症状
                        ,"IFNULL(SUM(asymptomatic_relative),0) as asymptomaticAdd"
                        //现有确诊
                        ,"IFNULL(SUM(cur_confirm),0) as curConfirm"
                ).eq("pro","福建")
                .likeRight("create_time",DatasUtils.getPlusDate(date,-1))
                );

        return buildRadarVo(todayList.get(0), yesterdayList.get(0));

    }

    /**
     * 生成雷达视图对象
     * @param todayMap 今日数据
     * @param yesterdayMap 昨日数据
     * @return 雷达视图对象
     */
    private RadarVo buildRadarVo(Map<String, Object> todayMap, Map<String, Object> yesterdayMap) {
        List<Long> todayData = buildRadarVoProps(todayMap);
        List<Long> yesterdayData = buildRadarVoProps(yesterdayMap);
        return new RadarVo(todayData,yesterdayData);
    }

    /**
     * 构建雷达视图对象属性
     * @param propsMap 数据键值对
     * @return 结果集合
     */
    private List<Long> buildRadarVoProps(Map<String, Object> propsMap) {
        List<Long> listProp = new ArrayList<>();
        listProp.add((Long) propsMap.get("confirmedAdd"));
        listProp.add((Long) propsMap.get("nativeAdd"));
        listProp.add((Long) propsMap.get("asymptomaticAdd"));
        listProp.add((Long) propsMap.get("curConfirm"));
        return listProp;
    }

}
