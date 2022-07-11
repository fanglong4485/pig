package com.pig4cloud.pig.datas.controller;

import cn.hutool.core.util.ObjectUtil;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.datas.vo.CityInfo;
import com.pig4cloud.pig.datas.vo.RadarVo;
import com.pig4cloud.pig.datas.entity.DataInOutNum;
import com.pig4cloud.pig.datas.service.DataChinaCityService;
import com.pig4cloud.pig.datas.service.DataInOutNumService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
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

    private final DataInOutNumService inOutNumService;

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

    @Operation(summary = "雷达图", description = "雷达图")
    @GetMapping("/radarData")
    public R getRadarData(@PathParam("date") String date){
        RadarVo result = dataChinaCityService.getRadarData(date);
        if (ObjectUtil.isNull(result)){
            return R.failed("雷达图查询为空！");
        } else {
            return R.ok(result);
        }
    }

    @Operation(summary = "福建现有确诊地图", description = "福建现有确诊地图")
    @GetMapping("/mapData")
    public R getMapData(@PathParam("date") String date){
        List<Long> result = dataChinaCityService.getMapData(date);
        if (ObjectUtil.isNull(result)){
            return R.failed("市级数据查询为空！");
        } else {
            return R.ok(result);
        }
    }


    @Operation(summary = "危险等级地图", description = "危险等级地图")
    @GetMapping("/dangerLevel")
    public R getDangerLevel(@PathParam("date") String date) {
        //DangerLevel result = dangerLevelService.getOne(new QueryWrapper<DangerLevel>().likeRight("date", date));
        //if (ObjectUtil.isNull(result)){
        //    return R.failed("危险等级查询为空！");
        //} else {
        //    return R.ok(result);
        //}
        return R.failed();
    }


    @Operation(summary = "飞线数据", description = "飞线数据")
    @GetMapping("/flyLine")
    public R getFlyLine(@PathParam("date") String date) {
        List<DataInOutNum> result = inOutNumService.getFlyLineData(date);
        if (ObjectUtil.isNull(result)) {
            return R.failed("飞线数据查询为空！");
        } else {
            return R.ok(result);
        }

    }

    //@Operation(summary = "总结数据", description = "总结数据")
    //@GetMapping("/summary")
    //public R getSummary(@PathParam("date") String date) {
    //    List<Summary> result = summaryService.list(new QueryWrapper<Summary>().eq("create_time", date));
    //
    //    if (result.size() == 0) {
    //        return R.failed("总结数据查询为空！");
    //    } else {
    //        return R.ok(result);
    //    }
    //
    //}


    @Operation(summary = "趋势折线图", description = "趋势折线图")
    @GetMapping("/citiesTrend")
    public R getCitiesTrend() {
        Map result = dataChinaCityService.getCitiesTrend();
        return R.ok(result);
    }

}
