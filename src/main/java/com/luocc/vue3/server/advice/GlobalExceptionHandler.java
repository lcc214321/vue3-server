package com.luocc.vue3.server.advice;

import com.luocc.vue3.server.api.ApiCode;
import com.luocc.vue3.server.api.ApiResult;
import com.luocc.vue3.server.exception.ServerException;
import com.luocc.vue3.server.util.StackUtils;
import com.luocc.vue3.server.util.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常
 */
@Slf4j
@RestController
@ControllerAdvice
public class GlobalExceptionHandler implements ErrorController {

    private static final String ERROR_PATH = "/error";

    // 处理所有不可知的异常
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResult handlerException(Exception ex) {

        // 打印堆栈信息
        log.error(StackUtils.getStackTrace(ex));

        return ApiResult.fail(ex.getMessage());
    }

    // 处理服务端自定义异常
    @ExceptionHandler(ServerException.class)
    public ApiResult handlerServerException(ServerException ex, HttpServletResponse response) {

        // 打印堆栈信息
        log.error(StackUtils.getStackTrace(ex));

        // 设置响应状态码
        response.setStatus(ex.getApiCode().getCode());

        return ApiResult.of(ex.getApiCode());
    }

    // 重写404错误
    @RequestMapping(value = ERROR_PATH)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public void handleError(
            HttpServletResponse response
    ) {

        WebUtils.renderJson(response, ApiResult.of(ApiCode.NOT_FOUND));
    }

    // 处理请求方法不对的异常（405）
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public void handHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, HttpServletResponse response) {

        // 打印堆栈信息
        log.error(StackUtils.getStackTrace(ex));

        WebUtils.renderJson(response, ApiResult.of(ApiCode.METHOD_NOT_ALLOWED));
    }

    // 处理接口数据验证异常
    // 实际的开发场景中，异常是区分很多类别的，不同类别的异常需要给用户不同的反馈。有使用到注解式参数校验，但校验不通过原因并没有以有效的方式告之给前端应用
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResult handMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        // 打印堆栈信息
        log.error(StackUtils.getStackTrace(ex));

        return ApiResult.fail(StackUtils.getMessage(ex));
    }

    // SQL异常 500(默认)
    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResult handSqlException(DataAccessException e) {
        log.error(StackUtils.getStackTrace(e));
        return ApiResult.of(ApiCode.FAIL, "内部异常");
    }

    @Override
    public String getErrorPath() {
        return null;
    }

}
