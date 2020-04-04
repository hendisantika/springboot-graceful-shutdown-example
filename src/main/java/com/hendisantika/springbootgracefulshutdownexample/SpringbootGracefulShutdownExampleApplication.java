package com.hendisantika.springbootgracefulshutdownexample;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PreDestroy;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static java.lang.Thread.sleep;

@Log4j2
@SpringBootApplication
public class SpringbootGracefulShutdownExampleApplication {
    //Thread Pool which runs some tasks
    ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        SpringApplication.run(SpringbootGracefulShutdownExampleApplication.class, args);
    }

    @PreDestroy
    public void destroy() {
        log.info("Triggered PreDestroy");

        //Verify if the threads have completed their tasks and then proceed with shutdown
        while (executor.getActiveCount() > 0) {
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info("Completed all active threads");
    }

    @RestController
    class UserController {

        @GetMapping("/hello")
        public String hello() throws InterruptedException {
            log.info("Waiting for 5 seconds...");
            executor.execute(this::task);
            return "Hello World! " + LocalDateTime.now();
        }

        private void task() {
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("Completed 5 seconds...");
        }
    }

}
