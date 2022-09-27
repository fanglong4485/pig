package com.pig4cloud.pig.analyse.core;

import com.pig4cloud.pig.analyse.entity.DangerLevel;
import com.pig4cloud.pig.analyse.entity.Summary;
import com.pig4cloud.pig.analyse.service.DangerLevelService;
import com.pig4cloud.pig.analyse.service.SummaryService;
import com.pig4cloud.pig.analyse.utils.AnalyseUtil;
import com.pig4cloud.pig.datas.utils.DatesUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

import static com.pig4cloud.pig.analyse.utils.AnalyseUtil.getDate;

/**
 * @author fanglong
 * @date 2022/7/6
 * @apiNote
 */
@Component
@RequiredArgsConstructor
public class DangerLevelAnalyzer {

    private final DangerLevelService dangerLevelService;

    private final SummaryService summaryService;

    /**
     * todo 如果时间循环到极限了怎么办？可以先查数据库里的最时间极限
     */
    Integer plusDay = 0;

	Integer plusDay2 = 0;

    @Scheduled(initialDelay = 3000, fixedDelay = 3000)
    public void calculateDangerLevel() {
        String newDate = AnalyseUtil.getPlusDate(getDate(new Date()), plusDay);
        DangerLevel da = dangerLevelService.calculate(newDate);
        dangerLevelService.saveOrUpdate(da);
        plusDay -= 1;
    }



    @Scheduled(initialDelay = 3000, fixedDelay = 3000)//启动就开始更新
    private void saveSummary() {
        String newDate = DatesUtil.getPlusDate(getDate(new Date()),plusDay2);
        //计算并保存分析总结
        List<Summary> summaries = summaryService.buildSummary(newDate);
        summaryService.saveOrUpdateBatch(summaries);
        plusDay2 -= 1;
    }
}
