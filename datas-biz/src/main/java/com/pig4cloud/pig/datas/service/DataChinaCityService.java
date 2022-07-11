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

package com.pig4cloud.pig.datas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.datas.vo.CityInfo;
import com.pig4cloud.pig.datas.vo.RadarVo;
import com.pig4cloud.pig.datas.entity.DataChinaCity;

import java.util.List;
import java.util.Map;

/**
 * 疫情数据表
 *
 * @author fanglong
 * @date 2022-07-01 02:57:58
 */
public interface DataChinaCityService extends IService<DataChinaCity> {

    List<Map<String, Object>> getOverview(String date);

    CityInfo getCityInfo(String date);

    List<?> getRankingInfo(String date);

    RadarVo getRadarData(String date);

    List<Long> getMapData(String date);

    Map getCitiesTrend();
}
