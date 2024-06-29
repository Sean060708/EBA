package com.EBA.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebAppConfig implements WebMvcConfigurer{
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	registry.addResourceHandler("/WEB-INF/**").addResourceLocations("/WEB-INF/");
	registry.addResourceHandler("/Index/**").addResourceLocations("/WEB-INF/Index/");
	}
}
