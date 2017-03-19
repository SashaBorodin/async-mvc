package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

/**
 * Created by Alexander on 17.03.2017.
 */
@RestController
public class AsyncCallableController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final TaskServiceImpl taskService;

    @Autowired
    public AsyncCallableController(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }

    @RequestMapping(value = "/callable", method = RequestMethod.GET, produces = "text/html")
    public Callable<String> executeSlowTask() {
        logger.info("Request received");

        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return taskService.execute();
            }
        };

        logger.info("Servlet thread released");

        return callable;
    }
}
