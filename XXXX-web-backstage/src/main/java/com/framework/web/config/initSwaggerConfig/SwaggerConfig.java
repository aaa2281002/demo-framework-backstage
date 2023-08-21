package com.framework.web.config.initSwaggerConfig;

import org.springframework.boot.actuate.autoconfigure.endpoint.web.CorsEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementPortType;
import org.springframework.boot.actuate.endpoint.ExposableEndpoint;
import org.springframework.boot.actuate.endpoint.web.EndpointLinksResolver;
import org.springframework.boot.actuate.endpoint.web.EndpointMapping;
import org.springframework.boot.actuate.endpoint.web.EndpointMediaTypes;
import org.springframework.boot.actuate.endpoint.web.ExposableWebEndpoint;
import org.springframework.boot.actuate.endpoint.web.WebEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.annotation.ServletEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.service.Response;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author 龘鵺
 * @ClassName com.framework.api.config.initSwagger
 * @Description Swagger3初始化类
 * @Date 14/8/2023 上午9:37
 * @Version 1.0
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {
    //设置全局参数
    private List globalParamBuilder() {
        List<RequestParameter> list = new ArrayList<>();
        list.add(new RequestParameterBuilder().name("token").in(ParameterType.HEADER).description("令牌").required(true).build());
        return list;
    }

    //加载配置信息
    private ApiInfo apiInfo() {
        Contact contact = new Contact("测试", "", "test@test.com");
        return new ApiInfoBuilder().title("后台管理接口").description("后台管理接口文档").version("1.0版本").contact(contact).build();
    }

    //全局响应参数
    private List<Response> response() {
        List<Response> list = new ArrayList<>();
        list.add(new ResponseBuilder().code("code = 0").description("响应成功").build());
        list.add(new ResponseBuilder().code("code = 其他代码").description("大部分是错误代码，小部分是其他判断代码").build());
        return list;
    }

    //安全认证参数
    private List<SecurityScheme> security() {
        List<SecurityScheme> list = new ArrayList<>();
        list.add(new ApiKey("token", "token", "header"));
        return list;
    }

    /**
     * @return springfox.documentation.spring.web.plugins.Docket
     * @Titel 初始化swagger
     * @Description 初始化swagger
     * @Author 龘鵺
     * @DateTime 14/8/2023 上午11:48
     */
    @Bean
    public Docket initConfig() {
        return new Docket(DocumentationType.OAS_30).globalRequestParameters(globalParamBuilder()).apiInfo(apiInfo())
                //设置全局响应参数
                .globalResponses(HttpMethod.GET, response())
                .globalResponses(HttpMethod.POST, response())
                .globalResponses(HttpMethod.DELETE, response())
                .globalResponses(HttpMethod.PUT, response())
                .securitySchemes(security())
                .select().apis(RequestHandlerSelectors.basePackage("com.framework.web.controller")).build();
    }

    /**
     * @param webEndpointsSupplier        1
     * @param servletEndpointsSupplier    2
     * @param controllerEndpointsSupplier 3
     * @param endpointMediaTypes          4
     * @param corsProperties              5
     * @param webEndpointProperties       6
     * @param environment                 7
     * @return org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping
     * @Titel 初见springboot和Swagger版本兼容问题
     * @Description 增加如下配置可解决Spring Boot 6.x 与Swagger 3.0.0 不兼容问题
     * @Author 龘鵺
     * @DateTime 14/8/2023 上午11:39
     */
    @Bean
    public WebMvcEndpointHandlerMapping webEndpointServletHandlerMapping(WebEndpointsSupplier webEndpointsSupplier,
                                                                         ServletEndpointsSupplier servletEndpointsSupplier, ControllerEndpointsSupplier controllerEndpointsSupplier,
                                                                         EndpointMediaTypes endpointMediaTypes, CorsEndpointProperties corsProperties, WebEndpointProperties webEndpointProperties, Environment environment) {
        List<ExposableEndpoint<?>> allEndpoints = new ArrayList();
        Collection<ExposableWebEndpoint> webEndpoints = webEndpointsSupplier.getEndpoints();
        allEndpoints.addAll(webEndpoints);
        allEndpoints.addAll(servletEndpointsSupplier.getEndpoints());
        allEndpoints.addAll(controllerEndpointsSupplier.getEndpoints());
        String basePath = webEndpointProperties.getBasePath();
        EndpointMapping endpointMapping = new EndpointMapping(basePath);
        boolean shouldRegisterLinksMapping = this.shouldRegisterLinksMapping(webEndpointProperties, environment, basePath);
        return new WebMvcEndpointHandlerMapping(endpointMapping, webEndpoints, endpointMediaTypes, corsProperties.toCorsConfiguration(), new EndpointLinksResolver(allEndpoints, basePath), shouldRegisterLinksMapping, null);
    }

    //注册链接映射
    private boolean shouldRegisterLinksMapping(WebEndpointProperties webEndpointProperties, Environment environment, String basePath) {
        return webEndpointProperties.getDiscovery().isEnabled() && (StringUtils.hasText(basePath) || ManagementPortType.get(environment).equals(ManagementPortType.DIFFERENT));
    }
}
