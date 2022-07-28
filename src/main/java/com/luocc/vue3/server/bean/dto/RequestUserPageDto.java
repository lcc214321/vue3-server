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
public class RequestUserPageDto {

    @NotNull(message = "pageNo不能为空")
    @Min(value = 1, message = "pageNo最小值为1")
    @ApiModelProperty(value = "当前页数", position = 1, required = true)
    private Integer pageNo;

    @NotNull(message = "pageSize不能为空")
    @Min(value = 1, message = "pageSize最小值为10")
    @Max(value = 100, message = "pageSize最大值为100")
    @ApiModelProperty(value = "每页个数", position = 2, required = true)
    private Integer pageSize;

    @ApiModelProperty(value = "账户", position = 3)
    private String userName;

    @ApiModelProperty(value = "性别", position = 4)
    private Integer sex;

    @ApiModelProperty(value = "是否有效：1-有效；2-无效", position = 5)
    private Integer isDisabled;

}
