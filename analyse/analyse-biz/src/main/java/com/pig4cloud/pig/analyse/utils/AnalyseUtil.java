package com.pig4cloud.pig.analyse.utils;

import cn.hutool.core.date.DateUtil;
import lombok.experimental.UtilityClass;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author fanglong
 * @date 2022/7/6
 * @apiNote
 */
@UtilityClass
public class AnalyseUtil {

    public static String getDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }

    public static String getPlusDate(String originDay, int days) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date originDate = DateUtil.parse(originDay);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(originDate);
        calendar.add(Calendar.DATE, days);
        return formatter.format(calendar.getTime());
    }
}
