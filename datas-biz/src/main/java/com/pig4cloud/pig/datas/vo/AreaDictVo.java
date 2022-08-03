package com.pig4cloud.pig.datas.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author fanglong
 * @date 2022/8/3
 * @apiNote
 */
@Data
@AllArgsConstructor
public class AreaDictVo {

	/**
	 * 标签
	 */
	@Schema(description ="标签")
	String label;

	/**
	 * 值
	 */
	@Schema(description ="值")
	String value;
}
