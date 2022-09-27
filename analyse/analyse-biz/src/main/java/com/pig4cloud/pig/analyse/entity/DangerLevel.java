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

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pig4cloud.pig.common.mybatis.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;

/**
 * 危险等级
 *
 * @author fanglong
 * @date 2022-07-06 07:59:51
 */
@Data
@TableName("danger_level")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "危险等级")
@AllArgsConstructor
public class DangerLevel extends BaseEntity {

    /**
     * id
     */
    //@TableId(type = IdType.ASSIGN_ID)
    //@Schema(description ="id")
    //private Integer id;

    /**
     * fuzhou
     */
    @Schema(description ="fuzhou")
    private BigDecimal fuzhou;

    /**
     * xiamen
     */
    @Schema(description ="xiamen")
    private BigDecimal xiamen;

    /**
     * putian
     */
    @Schema(description ="putian")
    private BigDecimal putian;

    /**
     * sanming
     */
    @Schema(description ="sanming")
    private BigDecimal sanming;

    /**
     * quanzhou
     */
    @Schema(description ="quanzhou")
    private BigDecimal quanzhou;

    /**
     * zhangzhou
     */
    @Schema(description ="zhangzhou")
    private BigDecimal zhangzhou;

    /**
     * nanping
     */
    @Schema(description ="nanping")
    private BigDecimal nanping;

    /**
     * longyan
     */
    @Schema(description ="longyan")
    private BigDecimal longyan;

    /**
     * ningde
     */
    @Schema(description ="ningde")
    private BigDecimal ningde;


}
