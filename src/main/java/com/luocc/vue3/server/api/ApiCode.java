package com.luocc.vue3.server.api;

/**
 * REST API 响应码
 */
public enum ApiCode {

    SUCCESS(200, "操作成功"),
    UNAUTHORIZED(401, "非法访问"),
    NOT_PERMISSION(403, "权限不足"),
    NOT_FOUND(404, "资源不存在"),
    METHOD_NOT_ALLOWED(405, "方法不被允许"),
    TOO_MANY_REQUESTS(429, "服务忙，稍后再试"),
    FAIL(500, "操作失败"),

    APP_INFO_NOT_EXIST(100001, "账户不存在"),
    PASSWORD_ERROR(100002, "密码错误"),
    ACCOUNT_DISABLED(100003, "账户被禁用"),
    ACCOUNT_ALREADY_EXISTS(100004, "账户已存在"),
            ;
    private final int code;
    private final String msg;

    ApiCode(final int code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ApiCode getApiCode(int code) {
        ApiCode[] ecs = ApiCode.values();
        for (ApiCode ec : ecs) {
            if (ec.getCode() == code) {
                return ec;
            }
        }
        return SUCCESS;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
