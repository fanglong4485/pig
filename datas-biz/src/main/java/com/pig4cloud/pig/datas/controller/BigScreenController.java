package com.pig4cloud.pig.datas.controller;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.datas.Vo.CityInfo;
import com.pig4cloud.pig.datas.Vo.RadarVo;
import com.pig4cloud.pig.datas.service.DataChinaCityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

/**
 * @author fanglong
 * @date 2022/7/3
 * @apiNote
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/bigScreen" )
@Tag(name = "大屏页面")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class BigScreenController {

    private final DataChinaCityService dataChinaCityService;

    /**
     * 数据概览
     * @param date 日期
     * @return 视图结果
     */
    @Operation(summary = "福建概况", description = "福建概况")
    @GetMapping("/overview" )
    //@PreAuthorize("@pms.hasPermission('datas_datachinacity_get')" )
    public R getFjOverview(@PathParam("date") String date) {
       List<Map<String, Object>> result = dataChinaCityService.getOverview(date);
        if (ObjectUtil.isNull(result)){
            return R.failed("福建概况查询为空！");
        } else {
            return R.ok(result);
        }
    }

    @Operation(summary = "各城市柱状图", description = "各城市柱状图")
    @GetMapping("/city" )
    public R getCityInfo(@PathParam("date") String date) {
        CityInfo result = dataChinaCityService.getCityInfo(date);
        if (ObjectUtil.isNull(result)){
            return R.failed("各城市柱状图查询为空！");
        } else {
            return R.ok(result);
        }
    }

    @Operation(summary = "各城市排名", description = "各城市排名")
    @GetMapping("/ranking")
    public R getRankingInfo(@PathParam("date") String date){
        List<?> result = dataChinaCityService.getRankingInfo(date);
        if (ObjectUtil.isNull(result)){
            return R.failed("新增排名查询为空！");
        } else {
            return R.ok(result);
        }
    }

    @GetMapping("/radarData")
    public R getRadarData(@PathParam("date") String date){
        RadarVo result = dataChinaCityService.getRadarData(date);
        if (ObjectUtil.isNull(result)){
            return R.failed("雷达图查询为空！");
        } else {
            return R.ok(result);
        }
    }
}
