package com.pig4cloud.pig.analyse;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.pig4cloud.pig.analyse.api.feign.RemoteDataService;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.core.util.SpringContextHolder;
import com.pig4cloud.pig.datas.entity.DataInOutNum;
import com.pig4cloud.pig.datas.entity.DataInOutNumCurve;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author fanglong
 * @date 2022/7/9
 * @apiNote
 */
@SpringBootTest
class AnalyseTest {


	@Test
	void feignTest(){
		//带参数feign调用测试总结：
		//feign请求执行步骤如下：
		//1.调用remoteService的方法。@FeignClient注解里的value参数，指的是服务（模块）名。以对象为参数的话要加@SpringQueryMap注解。
		//2.执行接口里的语句。
		//3.如果发生异常，则执行remoteService的impl类，没有异常则跳过。
		//4.接口上必须有@Inner注解，不然汇报424权限错误。
		RemoteDataService remoteDataService = SpringContextHolder.getBean(RemoteDataService.class);
		DataInOutNum dataInOutNum = new DataInOutNum();
		dataInOutNum.setMark("测试类");
		R<List<DataInOutNum>> query = remoteDataService.queryDataInOutNum(dataInOutNum, SecurityConstants.FROM_IN);
		System.out.println("jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj"+query.toString());


	}

	@Test
	void test2(){
		String newDate = "2022-07-10";
		//LocalDateTime parse = LocalDateTime.parse(newDate);

		LocalDateTime parse = LocalDateTimeUtil.parse(newDate,"yyyy-MM-dd");

		System.out.println(parse);
	}

	@Test
	void test3(){
		RemoteDataService remoteDataService = SpringContextHolder.getBean(RemoteDataService.class);
		DataInOutNumCurve dataInOutNumCurve = new DataInOutNumCurve("厦门","in", "20220712");
		R<DataInOutNumCurve> dataInOutNumCurveR = remoteDataService.queryDataInOutNumCurve(dataInOutNumCurve, SecurityConstants.FROM_IN);
		System.out.println(dataInOutNumCurveR.getData());
	}

}
