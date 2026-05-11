package com.zoostarinc.mdm.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.Generated;

@Generated
@SpringBootApplication
@ComponentScan(basePackages = { "net.zoostar", "com.zoostarinc" })
public class MasterDataApi extends SpringBootServletInitializer implements WebMvcConfigurer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MasterDataApi.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(MasterDataApi.class, args);
	}

}
