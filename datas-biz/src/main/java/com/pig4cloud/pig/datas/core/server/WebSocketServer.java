//package com.pig4cloud.pig.datas.core.server;
//
//import cn.hutool.core.util.ObjectUtil;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//
//import io.prometheus.client.Summary;
//import lombok.AllArgsConstructor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.Map;
//
////@PreAuthorize("hasAnyAuthority('ROLE_bigScreen')")
////@ServerEndpoint(value = "/ws/{username}")
//@Component
//@AllArgsConstructor
//public class WebSocketServer {
//
//    private final static Logger log = LoggerFactory.getLogger(WebSocketServer.class);
//
//    private SimpMessagingTemplate messagingTemplate;
//
//    private ProvinceService provinceService;
//
//    private CityService cityService;
//
//    private PredictionService predictionService;
//
//    private RankingService rankingService;
//
//    private RadarService radarService;
//
//    private ProvinceMapService provinceMapService;
//
//    private CityMapService cityMapService;
//
//    private InOutNumService inOutNumService;
//
//    private DangerLevelService dangerLevelService;
//
//    private ChinaCityService chinaCityService;
//
//    private SummaryService summaryService;
//
//
//    @Scheduled(fixedDelay = 3000)
//    public void province() {
//        List<TitleDate> result = provinceService.listTitleDate("");
//        messagingTemplate.convertAndSend("/bigScreen/province", result);
//    }
//    @Scheduled(fixedDelay = 3000)
//    public void city(){
//        CityVo result = cityService.getCityInfo(new QueryWrapper<ProvinceCity>());
//        messagingTemplate.convertAndSend("/bigScreen/city", result);
//    }
//    @Scheduled(fixedDelay = 3000)
//    public void ranking(){
//        String date = "";
//        List<?> result = rankingService.getRankingInfo(date);
//        messagingTemplate.convertAndSend("/bigScreen/ranking", result);
//    }
//
//    @Scheduled(fixedDelay = 3000)
//    public void radarData(){
//        RadarVo result = radarService.getRadarData("");
//        messagingTemplate.convertAndSend("/bigScreen/radarData", result);
//    }
//    @Scheduled(fixedDelay = 3000)
//    public void provinceMapData(){
//        List<Integer> result = provinceMapService.getProvinceMapData("");
//        messagingTemplate.convertAndSend("/bigScreen/provinceMapData", result);
//    }
//
//    @Scheduled(fixedDelay = 3000)
//    public void cityMapData() {
//        List<Integer> result = cityMapService.getCityMapData();
//        messagingTemplate.convertAndSend("/bigScreen/cityMapData", result);
//    }
//
//    @Scheduled(fixedDelay = 3000)
//    public void dangerLevel() {
//        QueryWrapper<DangerLevel> qw = new QueryWrapper<>();
//        qw.likeRight("date", MyUtils.today());
//        DangerLevel result = dangerLevelService.getOne(qw);
//        if (ObjectUtil.isNull(result)) {
//            messagingTemplate.convertAndSend("/bigScreen/dangerLevel", Result.error("危险等级查询结果为空！"));
//        } else {
//            messagingTemplate.convertAndSend("/bigScreen/dangerLevel", result);
//        }
//
//    }
//
//    @Scheduled(fixedDelay = 3000)
//    public void getCitiesTrend() {
//        Map result = chinaCityService.getCitiesTrend();
//        messagingTemplate.convertAndSend("/bigScreen/citiesTrend", result);
//    }
//
//    @Scheduled(fixedDelay = 3000)
//    public void getFlyLineData() {
//        List<InOutNum> result = inOutNumService.getFlyLineData(MyUtils.getYesterday());
//        if (result.size() == 0) {
//            messagingTemplate.convertAndSend("/bigScreen/flyLineData", Result.error("飞线数据获取异常！"));
//        } else {
//            messagingTemplate.convertAndSend("/bigScreen/flyLineData", result);
//        }
//    }
//
//    @Scheduled(fixedDelay = 3000)
//    public void getSummary() {
//        QueryWrapper<Summary> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("create_time", MyUtils.today());
//        List<Summary> result = summaryService.list(queryWrapper);
//        messagingTemplate.convertAndSend("/bigScreen/summary", result);
//    }
//
//}
//
