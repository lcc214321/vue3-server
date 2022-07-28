package com.luocc.vue3.server.bean.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("t_user")
public class User {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 账户
     */
    @TableField("username")
    private String username;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 性别：1-男；2-女；
     */
    @TableField("sex")
    private Integer sex;

    /**
     * 状态：1-启用；2-禁用；
     */
    @TableField("is_disabled")
    private Integer isDisabled;

    /**
     * 创建时间
     */
    @TableField("create_at")
    private Date createAt;

    /**
     * 创建人
     */
    @TableField("create_by")
    private String createBy;

}
