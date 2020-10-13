package com.kishor.springmvc.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import io.swagger.annotations.ApiKeyAuthDefinition;

import io.swagger.annotations.SecurityDefinition;
import io.swagger.annotations.SwaggerDefinition;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2

public class SwaggerConfig {

	
	@Bean
	public Docket SwaggerConfiguration()
	{
		
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(Predicates.or(PathSelectors.ant("/api/*"),PathSelectors.ant("/home/*")))
				.apis(RequestHandlerSelectors.basePackage("com.kishor"))
				.build()
				.apiInfo(apiInfo())
	            .securitySchemes(Arrays.asList(apiKey()))
	            .securityContexts(Arrays.asList(securityContext()));
	}
		
		private ApiInfo apiInfo() {
		    return new ApiInfoBuilder().title("Sig-Predict REST API Document")
		            .description("Going on")
		            .termsOfServiceUrl("localhost")
		            .version("1.0")
		            .build();
		}

		private ApiKey apiKey() {
		    return new ApiKey("jwtToken", "Authorization", "header");
		}
		
		private SecurityContext securityContext() {
			return SecurityContext.builder()
					.securityReferences(defaultAuth())
					.forPaths(Predicates.or(PathSelectors.ant("/api/*"),PathSelectors.ant("/home/*")))
					.build();
	}
	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference("jwtToken", authorizationScopes));
	}
				
	
}
