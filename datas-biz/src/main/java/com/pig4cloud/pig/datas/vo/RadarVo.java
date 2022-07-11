package com.pig4cloud.pig.datas.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author fanglong
 * @date 2022/7/4
 * @apiNote
 */
@Data
@AllArgsConstructor
public class RadarVo {
    private List<Long> todayValue;
    private List<Long> yesterdayValue;
}
