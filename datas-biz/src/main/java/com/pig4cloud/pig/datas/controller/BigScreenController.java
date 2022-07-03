package com.pig4cloud.pig.datas.controller;

import com.pig4cloud.pig.common.core.util.R;
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

    @Operation(summary = "福建概况", description = "福建概况")
    @GetMapping("/overview" )
    //@PreAuthorize("@pms.hasPermission('datas_datachinacity_get')" )
    public R getFjOverview(@PathParam("date") String date) {
       List<Map<String, Object>> results = dataChinaCityService.getOverview(date);
       return R.ok(results);
    }
}
