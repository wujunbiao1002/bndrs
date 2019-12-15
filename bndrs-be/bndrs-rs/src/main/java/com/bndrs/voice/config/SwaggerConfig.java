/*
 * 广州丰石科技有限公司拥有本软件版权2018并保留所有权利。
 * Copyright 2018, Guangzhou Rich Stone Data Technologies Company Limited,
 * All rights reserved.
 */

package com.bndrs.voice.config;

import org.apache.commons.lang3.time.StopWatch;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        StopWatch watch = new StopWatch();
        watch.start();
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()).useDefaultResponseMessages(false).select()
                .apis(RequestHandlerSelectors.basePackage("com.bndrs"))
                .paths(PathSelectors.any()).build();
        watch.stop();
        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("RESTful接口文档")
                .description("RESTful API Document").version("0.1.0").build();
    }
}
