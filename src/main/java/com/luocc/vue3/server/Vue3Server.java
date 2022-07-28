package com.luocc.vue3.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class Vue3Server {

    public static void main(String[] args) {

        SpringApplication.run(Vue3Server.class, args);

    }

}
