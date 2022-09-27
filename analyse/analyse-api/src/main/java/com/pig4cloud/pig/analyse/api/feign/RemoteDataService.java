package com.pig4cloud.pig.analyse.api.feign;


import com.pig4cloud.pig.analyse.api.feign.factory.RemoteDataServiceFallbackFactory;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.constant.ServiceNameConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.datas.dto.DataChinaCityDto;
import com.pig4cloud.pig.datas.dto.ProvinceDto;
import com.pig4cloud.pig.datas.entity.DataChinaCity;
import com.pig4cloud.pig.datas.entity.DataInOutNum;
import com.pig4cloud.pig.datas.entity.DataInOutNumCurve;
import com.pig4cloud.pig.datas.vo.ProvinceVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;


// feign-client。我的理解：第一个参数是哪个服务客户端（feign）发起的请求第二个参数是向哪个服务实例（服务）发起的请求
@FeignClient(contextId = "remoteDataService", value = ServiceNameConstants.DATAS_SERVICE
		,fallbackFactory = RemoteDataServiceFallbackFactory.class)//在nacos中心可以看到ServiceNameConstants.UMPS_SERVICE的值，可以核对。
public interface RemoteDataService {

	@GetMapping("/datainoutnum/query")
	R<List<DataInOutNum>> queryDataInOutNum(@SpringQueryMap DataInOutNum dataInOutNum, @RequestHeader(SecurityConstants.FROM) String from);

	@GetMapping("/datainoutnum/queryAttentionCities")
	R<List<DataInOutNum>> queryAttentionCities(@SpringQueryMap DataInOutNum dataInOutNum, @RequestHeader(SecurityConstants.FROM) String from);


	@GetMapping("/datachinacity/query")
	R<List<DataChinaCity>> queryDataChinaCity(@SpringQueryMap DataChinaCity dataChinaCity, @RequestHeader(SecurityConstants.FROM) String from);

	@GetMapping("/datachinacity/queryRecent")
	R<List<DataChinaCity>> queryRecent(@SpringQueryMap DataChinaCityDto dataChinaCity, @RequestHeader(SecurityConstants.FROM) String from);

	@GetMapping("/datachinacity/queryLocatedCities")
	R<List<DataChinaCity>> queryLocatedCities(@SpringQueryMap DataChinaCityDto dataChinaCity, @RequestHeader(SecurityConstants.FROM) String from);

	@GetMapping("/datachinacity/queryTrend")
	R<ProvinceVo> queryTrend(@SpringQueryMap ProvinceDto provinceDto, @RequestHeader(SecurityConstants.FROM) String from);


	@GetMapping("/datainoutnumcurve/query")
	R<DataInOutNumCurve> queryDataInOutNumCurve(@SpringQueryMap DataInOutNumCurve dataInOutNumCurve, @RequestHeader(SecurityConstants.FROM) String from);
}
