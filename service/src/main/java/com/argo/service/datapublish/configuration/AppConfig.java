package com.argo.service.datapublish.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(DataPublisherProperties.class)
public class AppConfig {
}
