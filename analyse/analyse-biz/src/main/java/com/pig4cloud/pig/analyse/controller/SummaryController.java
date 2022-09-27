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
import com.pig4cloud.pig.analyse.service.SummaryService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.analyse.entity.Summary;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;


/**
 * 分析总结
 *
 * @author fanglong
 * @date 2022-07-06 08:01:09
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/summary" )
@Tag(name = "分析总结管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class SummaryController {

    private final SummaryService summaryService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param summary 分析总结
     * @return
     */
    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('analyse_summary_get')" )
    public R getSummaryPage(Page page, Summary summary) {
        return R.ok(summaryService.page(page, Wrappers.query(summary)));
    }


    /**
     * 通过id查询分析总结
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id查询", description = "通过id查询")
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('analyse_summary_get')" )
    public R getById(@PathVariable("id" ) Integer id) {
        return R.ok(summaryService.getById(id));
    }

    /**
     * 新增分析总结
     * @param summary 分析总结
     * @return R
     */
    @Operation(summary = "新增分析总结", description = "新增分析总结")
    @SysLog("新增分析总结" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('analyse_summary_add')" )
    public R save(@RequestBody Summary summary) {
        return R.ok(summaryService.save(summary));
    }

    /**
     * 修改分析总结
     * @param summary 分析总结
     * @return R
     */
    @Operation(summary = "修改分析总结", description = "修改分析总结")
    @SysLog("修改分析总结" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('analyse_summary_edit')" )
    public R updateById(@RequestBody Summary summary) {
        return R.ok(summaryService.updateById(summary));
    }

    /**
     * 通过id删除分析总结
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id删除分析总结", description = "通过id删除分析总结")
    @SysLog("通过id删除分析总结" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('analyse_summary_del')" )
    public R removeById(@PathVariable Integer id) {
        return R.ok(summaryService.removeById(id));
    }

}
