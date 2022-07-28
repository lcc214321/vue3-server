package com.luocc.vue3.server.bean.dto;

import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ResponseUserLoginDto {

    private Integer userId;

    private String username;

    private String token;

}
