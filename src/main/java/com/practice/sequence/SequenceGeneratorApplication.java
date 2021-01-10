package com.practice.sequence;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SequenceGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SequenceGeneratorApplication.class, args);
	}
	
	@Bean
	public Docket swaggerConfiguration() {
		// Return a prepared Docket instance
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				//.paths(PathSelectors.ant("/api/*"))
				.apis(RequestHandlerSelectors.basePackage("com.practice.sequence"))
				.build()
				.apiInfo(apiDetails());
	}
	
	private ApiInfo apiDetails() {
		return new ApiInfo("Sequence generator API",
				"code practice for Sequence generator spring boot application",
				"1.0",
				"",
				new springfox.documentation.service.Contact("Shashank Singh Thakur", "", "shashankthakur@gmail.com"),
				"API License",
				"",
				Collections.emptyList());
	}
}
