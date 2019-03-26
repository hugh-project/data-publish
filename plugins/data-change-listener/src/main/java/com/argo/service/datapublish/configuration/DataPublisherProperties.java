package com.argo.service.datapublish.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("datapublisher")
public class DataPublisherProperties {

    private final Message message = new Message();
    public Message getMessage() {return message;}

    /**
     * Data source of region publish service
     */
    private final DataSource dataSource = new DataSource();
    public DataSource getDataSource() { return dataSource; }

    private final ThreadPool threadPool = new ThreadPool();
    public ThreadPool getThreadPool() {return threadPool;}

    public static class DataSource{
        private String url = "127.0.0.1";
        private String username = "root";
        private String password = "123456";
        private String databaseName = "core-master";

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getDatabaseName() {
            return databaseName;
        }

        public void setDatabaseName(String databaseName) {
            this.databaseName = databaseName;
        }
    }

    public static class Message {
        private int max = 10;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }
    }

    public static class ThreadPool {

        private int corePoolSize = 10;
        private int maxPoolSize = 100;
        private int queueCapacity = 500;
        private int keepAliveSeconds = 60;

        public int getCorePoolSize() {
            return corePoolSize;
        }

        public void setCorePoolSize(int corePoolSize) {
            this.corePoolSize = corePoolSize;
        }

        public int getMaxPoolSize() {
            return maxPoolSize;
        }

        public void setMaxPoolSize(int maxPoolSize) {
            this.maxPoolSize = maxPoolSize;
        }

        public int getQueueCapacity() {
            return queueCapacity;
        }

        public void setQueueCapacity(int queueCapacity) {
            this.queueCapacity = queueCapacity;
        }

        public int getKeepAliveSeconds() {
            return keepAliveSeconds;
        }

        public void setKeepAliveSeconds(int keepAliveSeconds) {
            this.keepAliveSeconds = keepAliveSeconds;
        }
    }

}
