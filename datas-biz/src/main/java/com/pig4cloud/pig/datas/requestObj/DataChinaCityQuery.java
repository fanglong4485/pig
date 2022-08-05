package com.pig4cloud.pig.datas.requestObj;

import com.pig4cloud.pig.datas.entity.DataChinaCity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author fanglong
 * @date 2022/8/5
 * @apiNote
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DataChinaCityQuery extends DataChinaCity {
	private String startDate;

	private String endDate;

}
