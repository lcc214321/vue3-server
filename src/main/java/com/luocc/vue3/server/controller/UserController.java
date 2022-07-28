package com.luocc.vue3.server.controller;

import com.luocc.vue3.server.api.ApiResult;
import com.luocc.vue3.server.bean.dto.*;
import com.luocc.vue3.server.bean.vo.PageInfo;
import com.luocc.vue3.server.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@Api(tags = "用户")
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation(
            value = "用户-登录"
    )
    @PostMapping(value = "/login")
    public ApiResult login(
            @Valid
            @RequestBody RequestUserLoginDto requestUserLoginDto
    ) {

        ResponseUserLoginDto responseUserLoginDto = userService.login(requestUserLoginDto);

        return ApiResult.ok(responseUserLoginDto);
    }

    @ApiOperation(
            value = "用户-查询"
    )
    @PostMapping(value = "/list")
    public ApiResult list(
            @Valid
            @RequestBody RequestUserPageDto requestUserPageDto
    ) {

        PageInfo<ResponseUserDto> pageInfo = userService.query(requestUserPageDto);

        return ApiResult.ok(pageInfo);
    }

    @ApiOperation(
            value = "用户-新增"
    )
    @PostMapping(value = "/add")
    public ApiResult add(
            @Valid
            @RequestBody RequestUserAddDto requestUserAddDto
    ) {

        userService.add(requestUserAddDto);

        return ApiResult.ok();
    }

    @ApiOperation(
            value = "用户-更新"
    )
    @PostMapping(value = "/update")
    public ApiResult update(
            @Valid
            @RequestBody RequestUserUpdateDto requestUserUpdateDto
    ) {

        userService.update(requestUserUpdateDto);

        return ApiResult.ok();
    }

    @ApiOperation(
            value = "用户-删除"
    )
    @PostMapping(value = "/del")
    public ApiResult del(
            @Valid
            @RequestBody RequestIdDto requestIdDto
    ) {

        userService.del(requestIdDto);

        return ApiResult.ok();
    }

    @ApiOperation(
            value = "用户-状态"
    )
    @PostMapping(value = "/disable")
    public ApiResult disable(
            @Valid
            @RequestBody RequestUserDisableDto requestUserDisableDto
    ) {

        userService.disable(requestUserDisableDto);

        return ApiResult.ok();
    }

}
