package com.pig4cloud.pig.analyse.api.feign.fallback;


import com.pig4cloud.pig.analyse.api.feign.RemoteDataService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.datas.dto.DataChinaCityDto;
import com.pig4cloud.pig.datas.dto.ProvinceDto;
import com.pig4cloud.pig.datas.entity.DataChinaCity;
import com.pig4cloud.pig.datas.entity.DataInOutNum;
import com.pig4cloud.pig.datas.entity.DataInOutNumCurve;
import com.pig4cloud.pig.datas.vo.ProvinceVo;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fanglong
 * @date 2022/7/11
 * @apiNote
 */
public class RemoteDataServiceFallbackImpl implements RemoteDataService {

	@Setter
	private Throwable cause;

	@Override
	public R<List<DataInOutNum>> queryDataInOutNum(DataInOutNum dataInOutNum, String from) {
		//接口调用异常时执行这里
		ArrayList<DataInOutNum> dataInOutNums = new ArrayList<>();
		dataInOutNums.add(dataInOutNum);
		return R.failed(dataInOutNums,"接口调用异常！请求头：" + from + "。" + cause.getMessage());
	}

	@Override
	public R<List<DataInOutNum>> queryAttentionCities(DataInOutNum dataInOutNum, String from) {
		//接口调用异常时执行这里
		ArrayList<DataInOutNum> dataInOutNums = new ArrayList<>();
		dataInOutNums.add(dataInOutNum);
		return R.failed(dataInOutNums,"接口调用异常！请求头：" + from + "。" + cause.getMessage());
	}

	@Override
	public R<List<DataChinaCity>> queryDataChinaCity(DataChinaCity dataChinaCity, String from) {
		//接口调用异常时执行这里
		ArrayList<DataChinaCity> dataChinaCities = new ArrayList<>();
		dataChinaCities.add(dataChinaCity);
		return R.failed(dataChinaCities,"接口调用异常！请求头：" + from + "。" + cause.getMessage());
	}

	@Override
	public R<List<DataChinaCity>> queryRecent(DataChinaCityDto dataChinaCity, String from) {
		//接口调用异常时执行这里
		ArrayList<DataChinaCity> dataChinaCities = new ArrayList<>();
		dataChinaCities.add(dataChinaCity);
		return R.failed(dataChinaCities,"接口调用异常！请求头：" + from + "。" + cause.getMessage());
	}

	@Override
	public R<List<DataChinaCity>> queryLocatedCities(DataChinaCityDto dataChinaCity, String from) {
		//接口调用异常时执行这里
		ArrayList<DataChinaCity> dataChinaCities = new ArrayList<>();
		dataChinaCities.add(dataChinaCity);
		return R.failed(dataChinaCities,"接口调用异常！请求头：" + from + "。" + cause.getMessage());
	}

	@Override
	public R<ProvinceVo> queryTrend(ProvinceDto provinceDto, String from) {
		//接口调用异常时执行这里
		return R.failed(provinceDto,"接口调用异常！请求头：" + from + "。" + cause.getMessage());
	}

	@Override
	public R<DataInOutNumCurve> queryDataInOutNumCurve(DataInOutNumCurve dataInOutNumCurve, String from) {
		//接口调用异常时执行这里
		return R.failed(dataInOutNumCurve,"接口调用异常！请求头：" + from + "。" + cause.getMessage());
	}
}
