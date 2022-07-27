package com.luocc.vue3.server.controller;

import com.luocc.vue3.server.api.ApiResult;
import com.luocc.vue3.server.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IUserService userService;

    @GetMapping("/service1")
    public ApiResult service1() {
        logger.info("exec test service1");

        return ApiResult.ok();
    }

}
