package com.fighter.blog.core.swagger;

import io.swagger.annotations.Api;
import lombok.Data;
import org.springframework.util.StringUtils;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Optional;

/**
 * @BelongsProject: fighter-blog-serve
 * @BeloongsPackage: com.fighter.blog.core.swagger
 * @ClassName: SwaggerDocketProperties
 * @CreateTime: 2021/12/20 0:11
 * @author: jie.zhang
 * @Email: 625103479@qq.com
 * @Desc:
 */
@Data
public class SwaggerDocketProperties {

    private String title;
    private String basePackage;
    private String description;

    public Docket toSwaggerDocket() {
        Docket docket = new Docket(DocumentationType.OAS_30)
                .apiInfo(toApiInfo())
                .useDefaultResponseMessages(false)
                .groupName(Optional.ofNullable(title).orElse("未命名的Swagger分组"))
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .build();
        return StringUtils.hasText(basePackage) ? docket.select().apis(RequestHandlerSelectors.basePackage(basePackage)).build() : docket;
    }

    private ApiInfo toApiInfo() {
        return new ApiInfoBuilder().title(title).description(description).build();
    }

}
