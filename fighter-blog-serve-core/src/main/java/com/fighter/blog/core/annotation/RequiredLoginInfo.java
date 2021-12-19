package com.fighter.blog.core.annotation;

/**
 * @BelongsProject: fighter-blog-serve
 * @BeloongsPackage: com.fighter.blog.core.annotation
 * @ClassName: RequiredLoginInfo
 * @CreateTime: 2021/12/20 0:54
 * @author: jie.zhang
 * @Email: 625103479@qq.com
 * @Desc: 登录信息必要检查
 */
// TODO: 该注解未完成
public @interface RequiredLoginInfo {

    /**
     * 失败消息
     * @return String
     */
    String message() default "登录信息不能为空";


}
