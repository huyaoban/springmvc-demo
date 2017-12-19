package com.huyaoban.springmvc.config;

import io.swagger.annotations.ApiOperation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableWebMvc
// 支持springmvc
@Component
public class SwaggerConfig {
	@Bean
	public Docket userApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.groupName("用户")
				.select()
				.apis(RequestHandlerSelectors
						.basePackage("com.oceanwing.qms.controller"))
				.apis(RequestHandlerSelectors
						.withMethodAnnotation(ApiOperation.class))
				.paths(PathSelectors.any()).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("QMS RESTful APIs")
				.description("QMS RESTful API Docs。").version("1.0.0").build();
	}
}

// 在实际开发中一般controller包里面根据不同的业务还要分为很多个包，
// 那么每一个包都需要在config里面写一对上面的api和info，api方法必须要加@Bean注解，不然识别不了
