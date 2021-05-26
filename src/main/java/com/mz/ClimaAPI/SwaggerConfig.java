package com.mz.ClimaAPI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.mz.ClimaAPI.controllers"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(getApiInfo())
				;
	}
	
	private ApiInfo getApiInfo() {
		return new ApiInfo(
				"Clima API",
				"API de Clima interplanetario.",
				"1.0",
				":)",
				new Contact("Martin Ezequiel Abogado", "https://www.linkedin.com/in/mezequielabogado/", "mezequielabogado@gmail.com"),
				"LICENSE -> Libre para todos :) ",
				"LICENSE URL -> No hace falta, jamas pedire algo! ",
				Collections.emptyList()
				);
	}
}