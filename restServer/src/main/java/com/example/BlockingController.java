package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Alexander on 17.03.2017.
 */

@RestController
public class BlockingController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TaskServiceImpl taskService;

    @RequestMapping(value = "/block", method = RequestMethod.GET, produces = "text/html")
    public String executeSlowTask() {
        logger.info("Request received");
        String result = taskService.execute();
        logger.info("Servlet thread released");

        return result;
    }
}
