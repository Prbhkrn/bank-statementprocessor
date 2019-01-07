package com.project.bankstatementprocessor.swaggergeneration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket produceApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.project.bankstatementprocessor.controller"))
				.paths(paths()).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Rabobank Customer Statement Processor Rest API's")
				.description("This page describes the usage of Rest API's for Statement Generation").version("1.0.0")
				.build();
	}

	private Predicate<String> paths() {
		return Predicates.and(PathSelectors.regex("/customer-api.*"), Predicates.not(PathSelectors.regex("/error.*")));
	}

}
