package com.pig4cloud.pig.analyse.api.feign.factory;

import com.pig4cloud.pig.analyse.api.feign.RemoteDataService;
import com.pig4cloud.pig.analyse.api.feign.fallback.RemoteDataServiceFallbackImpl;
import org.springframework.cloud.openfeign.FallbackFactory;

/**
 * @author fanglong
 * @date 2022/7/11
 * @apiNote
 */
public class RemoteDataServiceFallbackFactory implements FallbackFactory<RemoteDataService> {
	@Override
	public RemoteDataServiceFallbackImpl create(Throwable cause) {
		RemoteDataServiceFallbackImpl remoteDataServiceFallback = new RemoteDataServiceFallbackImpl();
		remoteDataServiceFallback.setCause(cause);
		return remoteDataServiceFallback;
	}
}
