package com.fighter.blog.core.annotation;

import com.fighter.blog.core.enums.ApiCheckType;

import java.lang.annotation.*;

/**
 * @BelongsProject: fighter-blog-serve
 * @BeloongsPackage: com.fighter.blog.core.annotation
 * @ClassName: NoPermissionCheck
 * @CreateTime: 2021/12/20 0:52
 * @author: jie.zhang
 * @Email: 625103479@qq.com
 * @Desc: 不检查权限注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoPermissionCheck {
    ApiCheckType type() default ApiCheckType.All_CHECK;
}
