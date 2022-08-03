package com.pig4cloud.pig.datas.utils;

import lombok.experimental.UtilityClass;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author fanglong
 * @date 2022/8/3
 * @apiNote
 */
@UtilityClass
public class CharUtil {
	/**
	 * 判断是否为汉字
	 * @param str
	 * @return
	 */
	public static boolean isChinese(String str) {
		if (str == null){
			return true;
		} else {
			String regEx = "[\u4e00-\u9fa5]";
			Pattern pat = Pattern.compile(regEx);
			Matcher matcher = pat.matcher(str);
			return matcher.find();
		}

	}
}
