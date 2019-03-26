package com.argo.service.datapublish.region;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for region data publisher
 */
@Configuration
public class RegionPublisherConfiguration {

    /**
     * Region publisher
     * @return
     */
    @Bean
    public RegionPublisher regionPublisher() {
        return new RegionPublisher();
    }


    public static final String MSGQUEUE_REGION_PUBLISH = "data-publish-region";

    @Bean
    public FanoutExchange regionPublishQueue() {
        return new FanoutExchange(MSGQUEUE_REGION_PUBLISH);
    }
}
