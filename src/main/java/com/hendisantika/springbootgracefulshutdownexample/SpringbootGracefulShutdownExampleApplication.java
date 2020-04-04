package com.hendisantika.springbootgracefulshutdownexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static java.lang.Thread.sleep;

@SpringBootApplication
public class SpringbootGracefulShutdownExampleApplication {
    //Thread Pool which runs some tasks
    ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        SpringApplication.run(SpringbootGracefulShutdownExampleApplication.class, args);
    }

    @RestController
    class UserController {

        @GetMapping("/hello")
        public String hello() throws InterruptedException {
            System.out.println("Waiting for 5 seconds...");
            executor.execute(this::task);
            return "Hello World";
        }

        private void task() {
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Completed 5 seconds...");
        }
    }

}
