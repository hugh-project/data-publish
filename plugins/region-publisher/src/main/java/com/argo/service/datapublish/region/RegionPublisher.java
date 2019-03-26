package com.argo.service.datapublish.region;

import com.ARGO.common.model.LogBean;
import com.ARGO.common.utils.LogHelper;
import com.ARGO.log.Logger;
import com.ARGO.log.LoggerFactory;
import com.argo.service.datapublish.DataChangeEvent;
import com.argo.service.datapublish.DataPublishEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;

import static com.argo.service.datapublish.configuration.DataChangeListenerConfig.dataChangeQueueName;


/**
 * Publisher for region master data
 */
public class RegionPublisher implements ApplicationListener<DataPublishEvent> {

    private Logger logger;
    @Autowired
    private LoggerFactory loggerFactory;
    @Autowired
    public Logger getLogger() {
        return logger = loggerFactory.getLogger(RegionPublisher.class);
    }



    /**
     * Listening data publish event and publish region data if it is relevant
     * @param dataPublishEvent dataPublishEvent
     */
    @Override
    @Async("DataPublisherExecutor")
    public void onApplicationEvent(DataPublishEvent dataPublishEvent) {
        DataChangeEvent chg = dataPublishEvent.getDataChangeEvent();
        if (chg == null || !DataChangeEvent.CATEGORY_MASTER.equalsIgnoreCase(chg.getCategory()) || !DataChangeEvent.DOMAIN_REGION.equalsIgnoreCase(chg.getDomain())) {
            // irrelevant event, ignore it
            return;
        }

        System.out.println("当前线程ID："+Thread.currentThread().getId());

        LogBean logBean = new LogBean("onApplicationEvent","MQ",dataChangeQueueName,chg.getKey(),null);
        logger.systemLog(LogHelper.Info(logBean,"receive {%s} message",dataChangeQueueName));


    }


}
