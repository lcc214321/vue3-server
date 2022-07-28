package com.luocc.vue3.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.luocc.vue3.server.bean.dto.ResponseUserDto;
import com.luocc.vue3.server.bean.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    User selectByUsername(
            @Param("username") String userName
    );

    List<ResponseUserDto> query(
            @Param("start_row") Integer startRow,
            @Param("offset_limit") Integer offsetLimit,
            @Param("username") String username,
            @Param("sex") Integer sex,
            @Param("is_disabled") Integer isDisabled
    );

    Integer queryCount(
            @Param("username") String username,
            @Param("sex") Integer sex,
            @Param("is_disabled") Integer isDisabled
    );

}
