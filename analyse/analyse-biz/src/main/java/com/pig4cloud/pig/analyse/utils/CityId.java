package com.pig4cloud.pig.analyse.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum CityId {
    FUZHOU(350100, "福州"),
    XIAMEN(350200, "厦门"),
    PUTIAN(350300, "莆田"),
    SANMING(350400, "三明"),
    QUANZHOU(350500, "泉州"),
    ZHANGHZOU(350600, "漳州"),
    NANPING(350700, "南平"),
    LONGYAN(350800, "龙岩"),
    NINGDE(350900, "宁德");

    private final Integer id;
    private final String cityName;

    public static CityId getCityById(String id) {
        for (CityId item : CityId.values()) {
            if (id.equals(String.valueOf(item.getId()))) {
                return item;
            }
        }
        return null;
    }
}
