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

import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.datas.service.SAdministrativeDivisionsService;
import com.pig4cloud.pig.datas.vo.AreaDictVo;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 行政区划表
 *
 * @author fl
 * @date 2022-08-03 18:09:40
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/sadministrativedivisions" )
@Tag(name = "行政区划表管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class SAdministrativeDivisionsController {

    private final  SAdministrativeDivisionsService sAdministrativeDivisionsService;

    @GetMapping("/{pcode:\\d+}")
    public R<List<AreaDictVo>> childAreaTranslate(@PathVariable Long pcode){
    	return R.ok(sAdministrativeDivisionsService.childAreaTranslate(pcode));

	}

}
