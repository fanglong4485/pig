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

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.annotation.Inner;
import com.pig4cloud.pig.datas.dto.DataChinaCityDto;
import com.pig4cloud.pig.datas.dto.ProvinceDto;
import com.pig4cloud.pig.datas.entity.DataChinaCity;
import com.pig4cloud.pig.datas.service.DataChinaCityService;
import com.pig4cloud.pig.datas.service.SAdministrativeDivisionsService;
import com.pig4cloud.pig.datas.utils.CharUtil;
import com.pig4cloud.pig.datas.vo.AreaDictVo;
import com.pig4cloud.pig.datas.vo.ProvinceVo;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 疫情数据表
 *
 * @author fanglong
 * @date 2022-07-01 02:57:58
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/datachinacity" )
@Tag(name = "疫情数据表管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class DataChinaCityController {

    private final  DataChinaCityService dataChinaCityService;

    private final SAdministrativeDivisionsService divisionsService;


    /**
     * 分页查询
     * @param page 分页对象
     * @param dataChinaCity 疫情数据表
     * @return
     */
    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('datas_datachinacity_get')" )
    public R getDataChinaCityPage(Page page, DataChinaCity dataChinaCity) {
    	//判断dataChinaCity对象携带的查询参数是城市代号还是城市名
		if (!CharUtil.isChinese(dataChinaCity.getPro())) {
			//只需要城市名查询条件
			String pcityCode = dataChinaCity.getCity();
			AreaDictVo cityDict = divisionsService.areaTranslate(Long.parseLong(pcityCode));
			String cityName = cityDict.getLabel();
			//定义需要省、市命中，需要过滤掉的字。SAdministrativeDivisionsService表里的城市有："省","市","县","区" 等字，而DataChinaCity表没有。
			String newCityName = CharSequenceUtil
					.replace(cityName, "市", "")
					.replace("区", "");
			dataChinaCity.setCity(newCityName);
			dataChinaCity.setPro(null);
		}
		return R.ok(dataChinaCityService.page(page, Wrappers.query(dataChinaCity)));
	}


    /**
     * 通过id查询疫情数据表
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id查询", description = "通过id查询")
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('datas_datachinacity_get')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(dataChinaCityService.getById(id));
    }

    /**
     * 新增疫情数据表
     * @param dataChinaCity 疫情数据表
     * @return R
     */
    @Operation(summary = "新增疫情数据表", description = "新增疫情数据表")
    @SysLog("新增疫情数据表" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('datas_datachinacity_add')" )
    public R save(@RequestBody DataChinaCity dataChinaCity) {
        return R.ok(dataChinaCityService.save(dataChinaCity));
    }

    /**
     * 修改疫情数据表
     * @param dataChinaCity 疫情数据表
     * @return R
     */
    @Operation(summary = "修改疫情数据表", description = "修改疫情数据表")
    @SysLog("修改疫情数据表" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('datas_datachinacity_edit')" )
    public R updateById(@RequestBody DataChinaCity dataChinaCity) {
        return R.ok(dataChinaCityService.updateById(dataChinaCity));
    }

    /**
     * 通过id删除疫情数据表
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id删除疫情数据表", description = "通过id删除疫情数据表")
    @SysLog("通过id删除疫情数据表" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('datas_datachinacity_del')" )
    public R removeById(@PathVariable Long id) {
        return R.ok(dataChinaCityService.removeById(id));
    }


	@Inner
    @GetMapping("/query")
	public R query(DataChinaCity dataChinaCity){
		List<DataChinaCity> list = dataChinaCityService.queryCities(dataChinaCity);
		if (list.isEmpty()) {
			return R.failed(list,"查询失败！");
		} else {
			return R.ok(list);
		}
	}

	@Inner
	@GetMapping("/queryRecent")
	public R queryRecent(DataChinaCityDto dataChinaCityDto){
		List<DataChinaCity> list = dataChinaCityService.queryRecent(dataChinaCityDto);
		if (list.isEmpty()) {
			return R.failed(list,"查询失败！");
		} else {
			return R.ok(list);
		}
	}

	@Inner
	@GetMapping("/queryLocatedCities")
	public R queryLocatedCities(DataChinaCityDto dataChinaCityDto){
		List<DataChinaCity> list = dataChinaCityService.queryLocatedCities(dataChinaCityDto);
		if (list.isEmpty()) {
			return R.failed(list,"查询失败！");
		} else {
			return R.ok(list);
		}
	}

	@Inner
	@GetMapping("/queryTrend")
	public R queryTrend(ProvinceDto provinceDto){
		ProvinceVo result = dataChinaCityService.queryTrend(provinceDto);
		if (ObjectUtil.isNull(result)) {
			return R.failed(result,"查询失败！");
		} else {
			return R.ok(result);
		}
	}

}
