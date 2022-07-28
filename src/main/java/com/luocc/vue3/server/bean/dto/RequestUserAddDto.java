package com.luocc.vue3.server.bean.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RequestUserAddDto {

    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名")
    private String username;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "描述")
    private Integer sex;

}
