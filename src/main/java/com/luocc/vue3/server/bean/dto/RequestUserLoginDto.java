package com.luocc.vue3.server.bean.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RequestUserLoginDto {

    @ApiModelProperty(value = "账户", position = 3)
    private String userName;

    @ApiModelProperty(value = "密码", position = 4)
    private String password;

}
