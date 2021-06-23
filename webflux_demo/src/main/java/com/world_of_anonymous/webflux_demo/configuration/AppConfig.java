package com.world_of_anonymous.webflux_demo.configuration;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class AppConfig {

  @Bean
  @Deprecated
  public static PropertyPlaceholderConfigurer getPropertyPlaceholderConfigurer() {
    PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
    ppc.setLocation(new ClassPathResource("application.properties"));
    ppc.setIgnoreUnresolvablePlaceholders(true);
    return ppc;
  }
}
