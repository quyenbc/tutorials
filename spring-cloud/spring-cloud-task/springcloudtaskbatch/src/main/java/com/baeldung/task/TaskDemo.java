package com.baeldung.task;

import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableTask
@EnableBatchProcessing
public class TaskDemo {

    private final static Logger LOGGER = Logger
        .getLogger(TaskDemo.class.getName());

    @Autowired
    private DataSource dataSource;

    @Bean
    public HelloWorldTaskConfigurer getTaskConfigurer()
    {
        return new HelloWorldTaskConfigurer(dataSource);
    }

    @Bean
    public TaskListener taskListener() {
        return new TaskListener();
    }

    public static void main(String[] args) {
        SpringApplication.run(TaskDemo.class, args);
    }

    @Component
    public static class HelloWorldApplicationRunner
        implements
        ApplicationRunner {

        @Override
        public void run(ApplicationArguments arg0)
            throws Exception {
            // TODO Auto-generated method stub
            LOGGER
                .info("Hello World from Spring Cloud Task!");
        }
    }
}