package com.luocc.vue3.server.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@EnableKnife4j
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Value("${spring.application.name}")
    private String application;

    /**
     * 创建API应用
     * apiInfo() 增加API相关信息
     * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
     * 本例采用指定扫描的包路径来定义指定要建立API的目录。
     */
    @Bean
    public Docket createRestApi() {

        List<ResponseMessage> responseMessages = new ArrayList<>();
        responseMessages.add(new ResponseMessageBuilder().code(200).message("请求成功").responseModel(new ModelRef("ApiError")).build());
        responseMessages.add(new ResponseMessageBuilder().code(404).message("找不到资源").responseModel(new ModelRef("ApiError")).build());
        responseMessages.add(new ResponseMessageBuilder().code(400).message("参数错误").responseModel(new ModelRef("ApiError")).build());
        responseMessages.add(new ResponseMessageBuilder().code(401).message("未登录").responseModel(new ModelRef("ApiError")).build());
        responseMessages.add(new ResponseMessageBuilder().code(403).message("权限不足").responseModel(new ModelRef("ApiError")).build());
        responseMessages.add(new ResponseMessageBuilder().code(404).message("未找到资源").responseModel(new ModelRef("ApiError")).build());
        responseMessages.add(new ResponseMessageBuilder().code(405).message("请求方法不对").responseModel(new ModelRef("ApiError")).build());
        responseMessages.add(new ResponseMessageBuilder().code(500).message("服务器内部错误").responseModel(new ModelRef("ApiError")).build());

        return new Docket(DocumentationType.SWAGGER_2)
                .globalResponseMessage(RequestMethod.GET, responseMessages)
                .globalResponseMessage(RequestMethod.POST, responseMessages)
                .globalResponseMessage(RequestMethod.PUT, responseMessages)
                .globalResponseMessage(RequestMethod.DELETE, responseMessages)
                .apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.luocc.vue3.server.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://项目实际地址/doc.html
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(application)
                .contact(new Contact("luocc", "yuntongxun.com", "luocc@yuntongxun.com")).version("1.0").build();
    }

}