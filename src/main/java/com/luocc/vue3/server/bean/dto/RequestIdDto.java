package com.luocc.vue3.server.bean.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RequestIdDto {

    @NotNull(message = "id不能为空")
    @ApiModelProperty(value = "主键ID", required = true)
    private Integer id;

}
