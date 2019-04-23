package com.argo.service.datapublish;

import java.util.LinkedList;
import java.util.List;

/**
 * Event for notify data change
 */
public class DataChangeEvent {
    public static final String CATEGORY_MASTER = "master";
    public static final String CATEGORY_TRANS = "trans";
    public static final String DOMAIN_REGION = "Region";
    public static final String DOMAIN_CATEGORY = "category";
    public static final String DOMAIN_UNIT = "unit";
    public static final String DOMAIN_PERSON = "person";
    public static final String DOMAIN_COMPANY = "company";
    public static final String DOMAIN_PARTYREL = "partyrel";
    public static final String DOMAIN_SUPPLY="supply";
    public static final String DOMAIN_REQUIRE="require";
    public static final String DOMAIN_BRAND = "brand";
//    public static final String DOMAIN_SUPPLY_REMOVE="supply-remove";

    /**
     * Category of changed data. Could be :
     * master : Master data
     * trans : Transactional data
     * log : Log data
     */
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Domain of data. Could be: Party, Product, Logistics, etc
     */
    private String domain;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * Name of entity.
     */
    private String entity;

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    /**
     * List of data been changed. Each of the item is Json representation of the identities of changed data
     */
    private List<String> items;

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }


    public DataChangeEvent category(String category) {
        setCategory(category);
        return this;
    }
    public DataChangeEvent key(String key) {
        setKey(key);
        return this;
    }
    public DataChangeEvent domain(String domain) {
        setDomain(domain);
        return this;
    }
    public DataChangeEvent entity(String entity) {
        setEntity(entity);
        return this;
    }
    public DataChangeEvent items(List<String> items) {
        setItems(items);
        return this;
    }
    public DataChangeEvent addItem(String item) {
        if (this.items == null) {
            this.items = new LinkedList<>();
        }
        this.items.add(item);
        return this;
    }
}
