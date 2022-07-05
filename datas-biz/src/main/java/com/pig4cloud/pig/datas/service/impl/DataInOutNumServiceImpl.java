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
import com.pig4cloud.pig.datas.entity.DataInOutNum;
import com.pig4cloud.pig.datas.mapper.DataInOutNumMapper;
import com.pig4cloud.pig.datas.service.DataInOutNumService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 
 *
 * @author fanglong
 * @date 2022-07-05 14:53:30
 */
@Service
@AllArgsConstructor
public class DataInOutNumServiceImpl extends ServiceImpl<DataInOutNumMapper, DataInOutNum> implements DataInOutNumService {

    private final DataInOutNumMapper inOutNumMapper;

    @Override
    public List<DataInOutNum> getFlyLineData(String date) {
       return inOutNumMapper.selectList(Wrappers.<DataInOutNum>lambdaQuery()
                .select(DataInOutNum::getTargetCity,DataInOutNum::getCityName,DataInOutNum::getValue)
                .eq(DataInOutNum::getDate,date)
                .eq(DataInOutNum::getProvinceName,"福建省")
                .eq(DataInOutNum::getType,"in")
                .last("LIMIT 12"));
    }
}
