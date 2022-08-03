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

package com.pig4cloud.pig.datas.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.datas.entity.SAdministrativeDivisions;
import com.pig4cloud.pig.datas.vo.AreaDictVo;

import java.util.List;

/**
 * 行政区划表
 *
 * @author fl
 * @date 2022-08-03 18:09:40
 */
public interface SAdministrativeDivisionsService extends IService<SAdministrativeDivisions> {

	/**
	 * 根据父级code查询字code和子城市名
	 * @param pcode 父级code
	 * @return 城市代号字典视图对象
	 */
	List<AreaDictVo> childAreaTranslate(Long pcode);

	AreaDictVo areaTranslate(long parseLong);
}
