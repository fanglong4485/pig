package com.pig4cloud.pig.datas.dto;

import com.pig4cloud.pig.datas.vo.ProvinceVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author fanglong
 * @date 2022/7/12
 * @apiNote
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProvinceDto extends ProvinceVo {
	private String startDate;
	private String endDate;
}
