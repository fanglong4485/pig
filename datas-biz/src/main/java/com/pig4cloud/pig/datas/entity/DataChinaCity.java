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
package com.pig4cloud.pig.datas.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pig4cloud.pig.common.mybatis.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 疫情数据表
 *
 * @author fanglong
 * @date 2022-07-01 02:57:58
 */
@Data
@TableName("data_china_city")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "疫情数据表")
public class DataChinaCity extends BaseEntity {

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description ="id")
    private Long id;

    /**
     * 省份
     */
    @Schema(description ="省份")
    private String pro;

    /**
     * 城市
     */
    @Schema(description ="城市")
    private String city;

    /**
     * 本土新增
     */
    @Schema(description ="本土新增")
    private String nativeRelative;

    /**
     * 累计无症状
     */
    @Schema(description ="累计无症状")
    private String asymptomatic;

    /**
     * 新增无症状
     */
    @Schema(description ="新增无症状")
    private String asymptomaticRelative;

    /**
     * 累计确诊
     */
    @Schema(description ="累计确诊")
    private String confirmed;

    /**
     * 新增确诊
     */
    @Schema(description ="新增确诊")
    private String confirmedRelative;

    /**
     * 累计死亡
     */
    @Schema(description ="累计死亡")
    private String died;

    /**
     * 新增死亡
     */
    @Schema(description ="新增死亡")
    private String diedRelative;

    /**
     * 现有确诊
     */
    @Schema(description ="现有确诊")
    private String curConfirm;

    /**
     * 累计治愈
     */
    @Schema(description ="累计治愈")
    private String crued;

    /**
     * 新增治愈
     */
    @Schema(description ="新增治愈")
    private String cruedRelative;

    /**
     * 城市代号
     */
    @Schema(description ="城市代号")
    private String cityCode;

    /**
     * 行政区编码
     */
    @Schema(description ="行政区编码")
    private String administrativeCode;


}