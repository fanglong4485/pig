package com.pig4cloud.pig.datas.vo;

import lombok.Data;

import java.util.List;

/**
 * @author fanglong
 * @date 2022/7/3
 * @apiNote
 */
@Data
public class CityInfo {

    /**
     * 地区
      */
    List<String> category;

    /**
     * 累计
     */
    List<Long> lineData;

    /**
     * 新增
     */
    List<Long> barData;

    /**
     * 治愈
     */
    List<Long> rateData;
}
