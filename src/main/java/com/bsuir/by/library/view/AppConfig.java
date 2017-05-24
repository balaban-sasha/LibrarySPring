package com.bsuir.by.library.view;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class AppConfig {
	
	@Bean
	public InternalResourceViewResolver viewResolver(){
		
		InternalResourceViewResolver iRVR = new InternalResourceViewResolver();
		iRVR.setPrefix("/WEB-INF/jsp/");
		iRVR.setSuffix(".jsp");
		return iRVR;
		
	}

}
