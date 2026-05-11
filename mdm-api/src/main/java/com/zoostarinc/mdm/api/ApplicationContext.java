package com.zoostarinc.mdm.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Generated
@Configuration
@RequiredArgsConstructor
public class ApplicationContext {

	@Value("${build.name}")
	private String buildName;

	@Value("${build.version}")
	private String buildVersion;

	@Value("${build.timestamp}")
	private String buildTimestamp;
	
	@Bean
	OpenAPI openAPI() {
		var version = new StringBuilder(buildVersion).append(".").append(buildName).append(".").append(buildTimestamp);
		return new OpenAPI().info(new Info().title("Master Data API").description("This API provides operations for Master Data Management.")
				.version(version.toString()).contact(new Contact().name("zoostar").email("devops@zoostar.net")));
	}

}
