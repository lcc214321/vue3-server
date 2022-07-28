package com.luocc.vue3.server.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebUtils {

    /**
     * 输出JSON格式数据
     */
    public static void renderJson(HttpServletResponse response, Object data) {
        // 设置编码格式
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object paramT, JsonGenerator paramJsonGenerator,
                                  SerializerProvider paramSerializerProvider) throws IOException {
                // 设置返回null转为 空字符串""
                paramJsonGenerator.writeString("");
            }
        });

        try {
            ServletOutputStream os = response.getOutputStream();
            os.write(objectMapper.writeValueAsString(data).getBytes("utf-8"));
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
