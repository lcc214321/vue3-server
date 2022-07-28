package com.luocc.vue3.server.util;

import org.springframework.util.CollectionUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.stream.Collectors;

public class StackUtils {

    /**
     * 获取堆栈信息
     */
    public static String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            throwable.printStackTrace(pw);
            return sw.toString();
        }
    }

    public static String getMessage(MethodArgumentNotValidException ex) {
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();

        if(CollectionUtils.isEmpty(allErrors)) {
            return "";
        }

        return allErrors.stream().map(s -> s.getDefaultMessage()).collect(Collectors.joining(";"));
    }

}
