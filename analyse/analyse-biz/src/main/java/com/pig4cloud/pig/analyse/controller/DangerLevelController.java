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

package com.pig4cloud.pig.analyse.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.analyse.service.DangerLevelService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.analyse.entity.DangerLevel;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;


/**
 * 危险等级
 *
 * @author fanglong
 * @date 2022-07-06 07:59:51
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/dangerlevel" )
@Tag(name = "危险等级管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class DangerLevelController {

    private final DangerLevelService dangerLevelService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param dangerLevel 危险等级
     * @return
     */
    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('analyse_dangerlevel_get')" )
    public R getDangerLevelPage(Page page, DangerLevel dangerLevel) {
        return R.ok(dangerLevelService.page(page, Wrappers.query(dangerLevel)));
    }


    /**
     * 通过id查询危险等级
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id查询", description = "通过id查询")
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('analyse_dangerlevel_get')" )
    public R getById(@PathVariable("id" ) Integer id) {
        return R.ok(dangerLevelService.getById(id));
    }

    /**
     * 新增危险等级
     * @param dangerLevel 危险等级
     * @return R
     */
    @Operation(summary = "新增危险等级", description = "新增危险等级")
    @SysLog("新增危险等级" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('analyse_dangerlevel_add')" )
    public R save(@RequestBody DangerLevel dangerLevel) {
        return R.ok(dangerLevelService.save(dangerLevel));
    }

    /**
     * 修改危险等级
     * @param dangerLevel 危险等级
     * @return R
     */
    @Operation(summary = "修改危险等级", description = "修改危险等级")
    @SysLog("修改危险等级" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('analyse_dangerlevel_edit')" )
    public R updateById(@RequestBody DangerLevel dangerLevel) {
        return R.ok(dangerLevelService.updateById(dangerLevel));
    }

    /**
     * 通过id删除危险等级
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id删除危险等级", description = "通过id删除危险等级")
    @SysLog("通过id删除危险等级" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('analyse_dangerlevel_del')" )
    public R removeById(@PathVariable Integer id) {
        return R.ok(dangerLevelService.removeById(id));
    }


    //@GetMapping("/test")
	//public R test(){
	//	RemoteInOutNumService remoteInOutNumService = SpringContextHolder.getBean(RemoteInOutNumService.class);
	//	DataInOutNum dataInOutNum = new DataInOutNum();
	//	dataInOutNum.setCityName("士大夫");
	//	R<DataInOutNum> info = remoteInOutNumService.query(SecurityConstants.FROM_IN);
	//	System.out.println("jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj"+info.toString());
	//	return R.ok(dataInOutNum,info.getData().toString());
	//}

}
