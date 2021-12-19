package com.fighter.blog.core.entity.properties;

import com.fighter.blog.core.swagger.SwaggerProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @BelongsProject: fighter-blog-serve
 * @BeloongsPackage: com.fighter.blog.core.entity.properties
 * @ClassName: FighterBlogProperties
 * @CreateTime: 2021/12/20 0:44
 * @author: jie.zhang
 * @Email: 625103479@qq.com
 * @Desc: 系统配置文件
 */
@Data
@ConfigurationProperties(prefix = "fighter.blog")
public class FighterBlogProperties {
    @NestedConfigurationProperty
    private SwaggerProperties swagger;
}
