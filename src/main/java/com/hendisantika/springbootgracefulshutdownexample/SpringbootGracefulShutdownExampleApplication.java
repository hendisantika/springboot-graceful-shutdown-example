package com.hendisantika.springbootgracefulshutdownexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@SpringBootApplication
public class SpringbootGracefulShutdownExampleApplication {
    //Thread Pool which runs some tasks
    ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        SpringApplication.run(SpringbootGracefulShutdownExampleApplication.class, args);
    }

}
