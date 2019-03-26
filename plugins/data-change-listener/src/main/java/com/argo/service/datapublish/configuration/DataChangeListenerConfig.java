package com.argo.service.datapublish.configuration;

import com.argo.service.datapublish.DataChangeListener;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataChangeListenerConfig {

    public static final String dataChangeQueueName = "data-change";

    @Bean(name = dataChangeQueueName)
    public Queue dataChangeQueue() {
        return new Queue(dataChangeQueueName);
    }

    @Bean
    public DataChangeListener dataChangeListener() {
        return new DataChangeListener();
    }
}
