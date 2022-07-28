package com.luocc.vue3.server.bean.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RequestUserDisableDto {

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "是否有效：1-有效；2-无效")
    private Integer isDisabled;

}
