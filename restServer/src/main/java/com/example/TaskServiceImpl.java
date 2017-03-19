package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by Alexander on 17.03.2017.
 */
@Service
public class TaskServiceImpl {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public String execute() {
        try {
            logger.info("Executing slow task...");
            TimeUnit.SECONDS.sleep(1);
            logger.info("Slow task executed.");
            return "Task finished";
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }
}
