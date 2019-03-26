package com.argo.service.datapublish.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <ul>
 * <li>公司名称 : 力微和</li>
 * <li>文件名称 : com.ARGO.service.datapublisher.configuration.ThreadPoolConfiguration</li>
 * <li>创建时间 : 2019年01月03日</li>
 * <li>描    述 :
 * <p>
 * </ul>
 *
 * @author XiaoLong.Tu
 * @since 1.0.0
 */
@Configuration
public class ThreadPoolConfiguration {

    @Bean("DataPublisherExecutor")
    public Executor MessageExecutor(DataPublisherProperties properties) {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程数
        executor.setCorePoolSize(properties.getThreadPool().getCorePoolSize());
        // 最大线程数
        executor.setMaxPoolSize(properties.getThreadPool().getMaxPoolSize());
        // 缓冲队列
        executor.setQueueCapacity(properties.getThreadPool().getQueueCapacity());
        // 允许线程的空闲时间
        executor.setKeepAliveSeconds(properties.getThreadPool().getKeepAliveSeconds());
        // 线程池名的前缀
        executor.setThreadNamePrefix("DataPubExec-");

        // 线程池对拒绝任务的处理策略：这里采用了CallerRunsPolicy策略，当线程池没有处理能力的时候，该策略会直接在 execute 方法的调用线程中运行被拒绝的任务；如果执行程序已关闭，则会丢弃该任务
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}
