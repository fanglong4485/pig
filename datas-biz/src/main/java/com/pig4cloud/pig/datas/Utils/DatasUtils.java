package com.pig4cloud.pig.datas.Utils;

import lombok.experimental.UtilityClass;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author fanglong
 * @date 2022/7/3
 * @apiNote
 */
@UtilityClass
public class DatasUtils {
    public static String getDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }
}
