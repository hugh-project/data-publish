package com.argo.service.datapublish.region;

import com.ARGO.common.model.LogBean;
import com.ARGO.common.utils.LogHelper;
import com.ARGO.log.Logger;
import com.ARGO.log.LoggerFactory;
import com.alibaba.fastjson.JSON;
import com.argo.service.datapublish.DataChangeEvent;
import com.argo.service.datapublish.DataPublishEvent;
import com.argo.service.datapublish.region.entity.Region;
import com.argo.service.datapublish.region.entity.RegionPublishMessage;
import com.argo.service.datapublish.region.entity.RegionRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static com.argo.service.datapublish.configuration.DataChangeListenerConfig.DataPublishExchangeName;
import static com.argo.service.datapublish.configuration.DataChangeListenerConfig.dataChangeQueueName;


/**
 * Publisher for region master data
 */
public class RegionPublisher implements ApplicationListener<DataPublishEvent> {
@Autowired
private RegionRepository regionRepository;
    private Logger logger;
    @Autowired
    private LoggerFactory loggerFactory;
    @Autowired
    private RabbitTemplate template;
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
        //查询组装数据，并发布到交换机
        List<String> items = chg.getItems();
        List<Region> allById = regionRepository.findAllById(items);
        RegionPublishMessage rpm = new RegionPublishMessage();
        rpm.setRegions(new LinkedList<>(allById));
        rpm.setCount(allById.size());
        rpm.setKey(chg.getKey());
        template.convertAndSend(DataPublishExchangeName,"", JSON.toJSONString(rpm));
    }



}
