package com.luocc.vue3.server.service.impl;

import com.luocc.vue3.server.api.ApiCode;
import com.luocc.vue3.server.bean.dto.*;
import com.luocc.vue3.server.bean.po.User;
import com.luocc.vue3.server.bean.vo.PageInfo;
import com.luocc.vue3.server.dao.UserMapper;
import com.luocc.vue3.server.exception.ServerException;
import com.luocc.vue3.server.service.IUserService;
import com.luocc.vue3.server.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public ResponseUserLoginDto login(RequestUserLoginDto requestUserLoginDto) {

        User user = userMapper.selectByUsername(requestUserLoginDto.getUserName());

        // 账户不存在
        if (Objects.isNull(user)) {
            throw new ServerException(ApiCode.APP_INFO_NOT_EXIST);
        }

        // 密码错误
        if (!user.getPassword().equalsIgnoreCase(requestUserLoginDto.getPassword())) {
            throw new ServerException(ApiCode.PASSWORD_ERROR);
        }

        // 账户被禁用
        if (1 != user.getIsDisabled()) {
            throw new ServerException(ApiCode.ACCOUNT_DISABLED);
        }

        // 令牌
        String token = TokenUtils.createJwt(
                user.getUsername(),
                UUID.randomUUID().toString(),
                "vue3-server",
                "令牌",
                user.getPassword()
        );

        return new ResponseUserLoginDto()
                .setUserId(user.getId())
                .setUsername(user.getUsername())
                .setToken(token);
    }

    @Override
    public PageInfo<ResponseUserDto> query(RequestUserPageDto requestUserPageDto) {
        // 当前页码
        Integer pageNo = requestUserPageDto.getPageNo();
        // 显示条数
        Integer pageSize = requestUserPageDto.getPageSize();
        // 账户
        String username = requestUserPageDto.getUserName();
        // 性别：1-男；2-女；
        Integer sex = requestUserPageDto.getSex();
        // 状态：1-启用；2-禁用；
        Integer isDisabled = requestUserPageDto.getIsDisabled();
        // 开始页码
        Integer startRow = (pageNo - 1) * pageSize;
        // 偏移量
        Integer offsetLimit = pageSize;
        // 结果集
        List<ResponseUserDto> list = userMapper.query(
                startRow, offsetLimit, ParamsUtil.joinLike(username), sex, isDisabled
        );
        // 总条数
        Integer total = userMapper.queryCount(ParamsUtil.joinLike(username), sex, isDisabled);
        // 分页封装
        return PageUtil.build(pageNo, pageSize, list, total);
    }

    @Override
    public void add(RequestUserAddDto requestUserAddDto) {

        User user = userMapper.selectByUsername(requestUserAddDto.getUsername());

        if(!Objects.isNull(user)) {
            throw new ServerException(ApiCode.ACCOUNT_ALREADY_EXISTS);
        }

        user = new User()
                .setUsername(requestUserAddDto.getUsername())
                .setPassword(requestUserAddDto.getPassword())
                .setSex(1)
                .setIsDisabled(1)
                .setCreateAt(DateUtil.getCurrent())
                .setCreateBy("admin");

        userMapper.insert(user);
    }

    @Override
    public void update(RequestUserUpdateDto requestUserUpdateDto) {
        // 账户ID
        Integer id = requestUserUpdateDto.getId();
        // 账户信息
        User user = userMapper.selectById(id);
        // 校参
        if (Objects.isNull(user)) {
            throw new ServerException(ApiCode.APP_INFO_NOT_EXIST);
        }
        user.setUsername(requestUserUpdateDto.getUsername())
                .setPassword(requestUserUpdateDto.getPassword())
                .setSex(requestUserUpdateDto.getSex());
        // 更新
        userMapper.updateById(user);
    }

    @Override
    public void del(RequestIdDto requestIdDto) {
        userMapper.deleteById(requestIdDto.getId());
    }

    @Override
    public void disable(RequestUserDisableDto requestUserDisableDto) {
        // 应用ID
        Integer id = requestUserDisableDto.getId();
        // 是否有效：1-有效；2-无效
        Integer isDisabled = requestUserDisableDto.getIsDisabled();
        // 应用信息
        User user = userMapper.selectById(id);
        // 校参
        if (Objects.isNull(user)) {
            throw new ServerException(ApiCode.APP_INFO_NOT_EXIST);
        }
        user.setIsDisabled(isDisabled);
        // 更新
        userMapper.updateById(user);
    }

}
