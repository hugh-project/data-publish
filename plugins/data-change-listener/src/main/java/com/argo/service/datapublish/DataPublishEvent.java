package com.argo.service.datapublish;

import org.springframework.context.ApplicationEvent;

/**
 * Event to notify all publishing plugins to publish relevant data
 */
public class DataPublishEvent extends ApplicationEvent {

    private DataChangeEvent dataChangeEvent;

    public DataPublishEvent(Object source) {
        super(source);
    }

    public DataPublishEvent(Object source, DataChangeEvent dataChanges) {
        super(source);
        dataChangeEvent = dataChanges;
    }

    public void setDataChangeEvent(DataChangeEvent dataChangeEvent) {
        this.dataChangeEvent = dataChangeEvent;
    }

    public DataChangeEvent getDataChangeEvent() {
        return dataChangeEvent;
    }
}
