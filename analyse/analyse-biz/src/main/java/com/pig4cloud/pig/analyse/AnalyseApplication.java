package com.pig4cloud.pig.analyse;

import com.pig4cloud.pig.common.feign.annotation.EnablePigFeignClients;
import com.pig4cloud.pig.common.security.annotation.EnablePigResourceServer;
import com.pig4cloud.pig.common.swagger.annotation.EnablePigDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
* @author pig archetype
* <p>
* 项目启动类
*/
@EnablePigDoc
@EnablePigFeignClients
@EnablePigResourceServer
@EnableDiscoveryClient
@SpringBootApplication
@EnableScheduling
public class AnalyseApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnalyseApplication.class, args);
    }

}
