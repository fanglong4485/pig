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
package com.pig4cloud.pig.analyse.entity;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pig4cloud.pig.common.mybatis.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分析总结
 *
 * @author fanglong
 * @date 2022-07-06 08:01:09
 */
@Data
@TableName("summary")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "分析总结")
public class Summary extends BaseEntity {

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description ="id")
    private Integer id;

    /**
     * indx
     */
    @Schema(description ="indx")
    private String indx;

    /**
     * text
     */
    @Schema(description ="text")
    private String text;


	public Summary(Integer id, String indx, String text, String createTime) {
		this.id = id;
		this.indx = indx;
		this.text = text;
		super.setCreateTime(LocalDateTimeUtil.parse(createTime,"yyyy-MM-dd"));
	}
}
