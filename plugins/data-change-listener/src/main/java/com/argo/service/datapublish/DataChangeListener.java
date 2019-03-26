package com.argo.service.datapublish;

import com.ARGO.common.model.LogBean;
import com.ARGO.common.utils.LogHelper;
import com.ARGO.log.Logger;
import com.ARGO.log.LoggerFactory;
import com.argo.service.datapublish.configuration.DataChangeListenerConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

import java.io.IOException;

/**
 * Component to listen the data change events come from DataLoader. And pass them to publisher
 * plugins by sending out data publish events.
 */
public class DataChangeListener implements ApplicationEventPublisherAware {

    private Logger logger;
    @Autowired
    private LoggerFactory loggerFactory;
    @Autowired
    public Logger getLogger() {
        return logger = loggerFactory.getLogger(DataChangeListener.class);
    }

    private ApplicationEventPublisher eventPublisher;

    /**
     * Listen the RabbitQueue data-change
     * @param msg
     * @throws IOException
     */
    @RabbitListener(queues = DataChangeListenerConfig.dataChangeQueueName)
    public void onDataChange(String msg) {
        mapperMethod(msg);
    }

    //数据监听接收解析调用方法
    private void mapperMethod(String msg) {
        ObjectMapper mapper = new ObjectMapper();
        DataChangeEvent evt;
        try {
            evt = mapper.readValue(msg, DataChangeEvent.class);
        } catch (IOException e) {
            LogBean logBean = new LogBean("mapperMethod","MQ",DataChangeListenerConfig.dataChangeQueueName,"mapperMethod",null);
            logger.systemLog(LogHelper.Error(e, logBean,"Convert data message failed! Err: {%s}", e.getMessage()));
            return;
        }

        // Send out DataPublishEvent to allow publisher plugins to do data publishing
        DataPublishEvent pubEvt = new DataPublishEvent(this, evt);
        eventPublisher.publishEvent(pubEvt);
    }


    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        eventPublisher = applicationEventPublisher;
    }
}
