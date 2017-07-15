package com.tradeaway.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by noumanm on 7/15/17.
 */

@Controller
@EnableAutoConfiguration
public class Application {
    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello SpringBoot";
    }

    public static void main (String args []) {
        SpringApplication.run(Application.class, args);
    }
}
