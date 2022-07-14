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

package com.pig4cloud.pig.datas.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.annotation.Inner;
import com.pig4cloud.pig.datas.entity.DataChinaCity;
import com.pig4cloud.pig.datas.entity.DataInOutNumCurve;
import com.pig4cloud.pig.datas.service.DataInOutNumCurveService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 
 *
 * @author fanglong
 * @date 2022-07-12 18:10:52
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/datainoutnumcurve" )
@Tag(name = "管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class DataInOutNumCurveController {

    private final  DataInOutNumCurveService dataInOutNumCurveService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param dataInOutNumCurve 
     * @return
     */
    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('datas_datainoutnumcurve_get')" )
    public R getDataInOutNumCurvePage(Page page, DataInOutNumCurve dataInOutNumCurve) {
        return R.ok(dataInOutNumCurveService.page(page, Wrappers.query(dataInOutNumCurve)));
    }


    /**
     * 通过id查询
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id查询", description = "通过id查询")
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('datas_datainoutnumcurve_get')" )
    public R getById(@PathVariable("id" ) Integer id) {
        return R.ok(dataInOutNumCurveService.getById(id));
    }

    /**
     * 新增
     * @param dataInOutNumCurve 
     * @return R
     */
    @Operation(summary = "新增", description = "新增")
    @SysLog("新增" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('datas_datainoutnumcurve_add')" )
    public R save(@RequestBody DataInOutNumCurve dataInOutNumCurve) {
        return R.ok(dataInOutNumCurveService.save(dataInOutNumCurve));
    }

    /**
     * 修改
     * @param dataInOutNumCurve 
     * @return R
     */
    @Operation(summary = "修改", description = "修改")
    @SysLog("修改" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('datas_datainoutnumcurve_edit')" )
    public R updateById(@RequestBody DataInOutNumCurve dataInOutNumCurve) {
        return R.ok(dataInOutNumCurveService.updateById(dataInOutNumCurve));
    }

    /**
     * 通过id删除
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id删除", description = "通过id删除")
    @SysLog("通过id删除" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('datas_datainoutnumcurve_del')" )
    public R removeById(@PathVariable Integer id) {
        return R.ok(dataInOutNumCurveService.removeById(id));
    }



    @Inner
	@GetMapping("/query")
	public R query(DataInOutNumCurve dataInOutNumCurve){
		DataInOutNumCurve data = dataInOutNumCurveService.getOne(Wrappers.lambdaQuery(dataInOutNumCurve));
		if (ObjectUtil.isNull(data)) {
			return R.failed(data,"查询失败！");
		} else {
			return R.ok(data);
		}

	}
}
