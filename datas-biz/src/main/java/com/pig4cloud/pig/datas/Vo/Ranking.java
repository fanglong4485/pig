package com.pig4cloud.pig.datas.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author fanglong
 * @date 2022/7/3
 * @apiNote
 */
@Data
@AllArgsConstructor
public class Ranking {

    /**
     * 地区
     */
    private String name;

    /**
     * 累计确诊
     */
    private Long value;
}
