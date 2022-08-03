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
package com.pig4cloud.pig.datas.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.datas.entity.SAdministrativeDivisions;
import com.pig4cloud.pig.datas.mapper.SAdministrativeDivisionsMapper;
import com.pig4cloud.pig.datas.service.SAdministrativeDivisionsService;
import com.pig4cloud.pig.datas.vo.AreaDictVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 行政区划表
 *
 * @author fl
 * @date 2022-08-03 18:09:40
 */
@Service
public class SAdministrativeDivisionsServiceImpl extends ServiceImpl<SAdministrativeDivisionsMapper, SAdministrativeDivisions> implements SAdministrativeDivisionsService {

	@Override
	public List<AreaDictVo> childAreaTranslate(Long pcode) {
		List<SAdministrativeDivisions> list = this.list(Wrappers.<SAdministrativeDivisions>lambdaQuery()
				.select(SAdministrativeDivisions::getCode, SAdministrativeDivisions::getName)
				.eq(SAdministrativeDivisions::getPcode, pcode));
		ArrayList<AreaDictVo> areaDictVos = new ArrayList<>();
		for (SAdministrativeDivisions item : list) {
			areaDictVos.add(new AreaDictVo(item.getName(),item.getCode().toString()));
		}
		return areaDictVos;
	}

	@Override
	public AreaDictVo areaTranslate(long parseLong) {

		SAdministrativeDivisions result = this.getOne(Wrappers.<SAdministrativeDivisions>lambdaQuery()
				.select(SAdministrativeDivisions::getCode, SAdministrativeDivisions::getName)
				.likeLeft(SAdministrativeDivisions::getCode, parseLong)
		);

		return new AreaDictVo(result.getName(), result.getCode().toString());
	}
}
