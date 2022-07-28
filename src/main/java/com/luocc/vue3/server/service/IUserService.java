package com.luocc.vue3.server.service;

import com.luocc.vue3.server.bean.dto.*;
import com.luocc.vue3.server.bean.vo.PageInfo;

public interface IUserService {

    ResponseUserLoginDto login(RequestUserLoginDto requestUserLoginDto);

    PageInfo<ResponseUserDto> query(RequestUserPageDto requestUserPageDto);

    void add(RequestUserAddDto requestUserAddDto);

    void update(RequestUserUpdateDto requestUserUpdateDto);

    void del(RequestIdDto requestIdDto);

    void disable(RequestUserDisableDto requestUserDisableDto);
}
