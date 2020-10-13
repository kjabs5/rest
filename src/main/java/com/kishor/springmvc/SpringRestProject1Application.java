package com.kishor.springmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import com.google.common.base.Predicates;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
//@EnableSwagger2
public class SpringRestProject1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestProject1Application.class, args);
	}
	
//	@Bean
//	public Docket SwaggerConfiguration()
//	{
//		
//		return new Docket(DocumentationType.SWAGGER_2)
//				.select()
//				.paths(Predicates.or(PathSelectors.ant("/api/*"),PathSelectors.ant("/home/*")))
//				.apis(RequestHandlerSelectors.basePackage("com.kishor"))
//				.build();
//				
//	}

}
