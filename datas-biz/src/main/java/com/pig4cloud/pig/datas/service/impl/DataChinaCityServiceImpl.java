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
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.datas.Utils.DatasUtils;
import com.pig4cloud.pig.datas.entity.DataChinaCity;
import com.pig4cloud.pig.datas.mapper.DataChinaCityMapper;
import com.pig4cloud.pig.datas.service.DataChinaCityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
        wrapper.select("IFNULL(SUM(confirmed_relative),0) as nativeAdd"//新增确诊
                ,"IFNULL(SUM(native_relative),0) as nativeAdd"//新增本土
                ,"IFNULL(SUM(asymptomatic_relative),0) as asymptomaticAdd"//新增无症状
                ,"IFNULL(SUM(cur_confirm),0) as curConfirm"//现有确诊
                ,"IFNULL(SUM(confirmed),0) as confirmed"//累计确诊
                ,"IFNULL(SUM(crued),0) as crued"//累计治愈
                ,"IFNULL(SUM(died),0) as died")//累计死亡
                .eq("pro","福建");
        if (CharSequenceUtil.hasEmpty(date)){
            wrapper.likeRight("create_time", DatasUtils.getDate(new Date()));
        } else {
            wrapper.likeRight("create_time",date);
        }

        return dataChinaCityMapper.selectMaps(wrapper);

    }
}
