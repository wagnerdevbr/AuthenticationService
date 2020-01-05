package com.wagnerdevbr.auth.config;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

public class WebConfiguration {
	@SuppressWarnings("rawtypes")
	@Bean
	ServletRegistrationBean h2servletRegistration() {
		@SuppressWarnings("unchecked")
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
		registrationBean.addUrlMappings("/console/*");
		return registrationBean;
	}
}
