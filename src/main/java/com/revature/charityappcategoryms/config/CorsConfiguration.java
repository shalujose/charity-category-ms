package com.revature.charityappcategoryms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
        String [] origins = { "http://localhost:4200", "https://charityapp-donor.firebaseapp.com",
               "https://admin.charityapp.in","https://app.charityapp.in","https://api.charityapp.in"};
  registry.addMapping("/**").allowedOrigins(origins)
          .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "HEAD").allowCredentials(true);
  }
}
