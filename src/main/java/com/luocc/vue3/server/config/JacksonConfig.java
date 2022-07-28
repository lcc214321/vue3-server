package com.luocc.vue3.server.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.math.BigDecimal;

@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.featuresToDisable(
                SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
                DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
        return builder;
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean(ObjectMapper.class)
    public ObjectMapper jacksonObjectMapper(
            @Qualifier("objectMapperBuilder") Jackson2ObjectMapperBuilder builder
    ) {

        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        // null值处理
        objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
                jsonGenerator.writeString("");
            }
        });
        // 声明自定义模块,配置double类型序列化配置
        SimpleModule module = new SimpleModule("DoubleSerializer", new Version(1, 0, 0, ""));
        // 注意Double和double需要分配配置
        module.addSerializer(Double.class, new DoubleSerializer());
        module.addSerializer(double.class, new DoubleSerializer());
        objectMapper.registerModule(module);

        return objectMapper;
    }

    static class DoubleSerializer extends JsonSerializer<Double> {

        @Override
        public void serialize(Double value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            BigDecimal d = new BigDecimal(value.toString());
            gen.writeNumber(d.stripTrailingZeros().toPlainString());
        }

        @Override
        public Class<Double> handledType() {
            return Double.class;
        }
    }

}

