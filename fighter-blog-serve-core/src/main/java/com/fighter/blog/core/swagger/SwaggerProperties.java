package com.fighter.blog.core.swagger;

import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * @BelongsProject: fighter-blog-serve
 * @BeloongsPackage: com.fighter.blog.core.swagger
 * @ClassName: SwaggerProperties
 * @CreateTime: 2021/12/20 0:11
 * @author: jie.zhang
 * @Email: 625103479@qq.com
 * @Desc:
 */
@Data
public class SwaggerProperties {

    private boolean enableSwagger = true;

    private List<SwaggerDocketProperties> apis = Collections.emptyList();
}
