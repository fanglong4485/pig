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
import java.math.BigDecimal;

/**
 * 
 *
 * @author fanglong
 * @date 2022-07-12 18:10:52
 */
@Data
@TableName("data_in_out_num_curve")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "")
public class DataInOutNumCurve extends BaseEntity {

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description ="id")
    private Integer id;

    /**
     * 唯一标识
     */
    @Schema(description ="唯一标识")
    private String mark;

    /**
     * 城市名
     */
    @Schema(description ="城市名")
    private String city;

    /**
     * 迁徙类型
     */
    @Schema(description ="迁徙类型")
    private String type;

    /**
     * 迁徙日期
     */
    @Schema(description ="迁徙日期")
    private String date;

    /**
     * 迁徙指数
     */
    @Schema(description ="迁徙指数")
    private BigDecimal value;


	public DataInOutNumCurve(String city, String type, String date) {
		this.city = city;
		this.type = type;
		this.date = date;
	}
}
