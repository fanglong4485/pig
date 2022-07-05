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

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.datas.entity.DataInOutNum;
import com.pig4cloud.pig.datas.service.DataInOutNumService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;


/**
 * 
 *
 * @author fanglong
 * @date 2022-07-05 14:53:30
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/datainoutnum" )
@Tag(name = "管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class DataInOutNumController {

    private final  DataInOutNumService dataInOutNumService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param dataInOutNum 
     * @return
     */
    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('datas_datainoutnum_get')" )
    public R getDataInOutNumPage(Page page, DataInOutNum dataInOutNum) {
        return R.ok(dataInOutNumService.page(page, Wrappers.query(dataInOutNum)));
    }


    /**
     * 通过id查询
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id查询", description = "通过id查询")
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('datas_datainoutnum_get')" )
    public R getById(@PathVariable("id" ) Integer id) {
        return R.ok(dataInOutNumService.getById(id));
    }

    /**
     * 新增
     * @param dataInOutNum 
     * @return R
     */
    @Operation(summary = "新增", description = "新增")
    @SysLog("新增" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('datas_datainoutnum_add')" )
    public R save(@RequestBody DataInOutNum dataInOutNum) {
        return R.ok(dataInOutNumService.save(dataInOutNum));
    }

    /**
     * 修改
     * @param dataInOutNum 
     * @return R
     */
    @Operation(summary = "修改", description = "修改")
    @SysLog("修改" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('datas_datainoutnum_edit')" )
    public R updateById(@RequestBody DataInOutNum dataInOutNum) {
        return R.ok(dataInOutNumService.updateById(dataInOutNum));
    }

    /**
     * 通过id删除
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id删除", description = "通过id删除")
    @SysLog("通过id删除" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('datas_datainoutnum_del')" )
    public R removeById(@PathVariable Integer id) {
        return R.ok(dataInOutNumService.removeById(id));
    }

}
