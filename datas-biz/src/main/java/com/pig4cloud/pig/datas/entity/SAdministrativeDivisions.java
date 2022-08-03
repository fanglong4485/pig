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
 * 行政区划表
 *
 * @author fl
 * @date 2022-08-03 18:09:40
 */
@Data
@TableName("s_administrative_divisions")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "行政区划表")
public class SAdministrativeDivisions extends BaseEntity {

    /**
     * 行政区划代码
     */
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description ="行政区划代码")
    private Integer code;

    /**
     * 名字
     */
    @Schema(description ="名字")
    private String name;

    /**
     * country:国家、province:省份（直辖市会在province和city显示）、city:市（直辖市会在province和city显示）、district:区县
     */
    @Schema(description ="country:国家、province:省份（直辖市会在province和city显示）、city:市（直辖市会在province和city显示）、district:区县")
    private String level;

    /**
     * 所属行政区划代码
     */
    @Schema(description ="所属行政区划代码")
    private Integer pcode;

    /**
     * 所属行政区划名字
     */
    @Schema(description ="所属行政区划名字")
    private String pname;

    /**
     * 行政区划完整名字
     */
    @Schema(description ="行政区划完整名字")
    private String fullname;

    /**
     * 经度
     */
    @Schema(description ="经度")
    private Double longitude;

    /**
     * 纬度
     */
    @Schema(description ="纬度")
    private Double latitude;


}
