package com.fighter.blog.core.enums;

/**
 * @BelongsProject: fighter-blog-serve
 * @BeloongsPackage: com.fighter.blog.core.enums
 * @ClassName: ApiCheckType
 * @CreateTime: 2021/12/20 0:51
 * @author: jie.zhang
 * @Email: 625103479@qq.com
 * @Desc: api 鉴权类型
 */
public enum ApiCheckType {
    /**
     * 既检测接口权限又检测登录权限
     */
    All_CHECK,

    /**
     * 不检查接口调用权限
     */
    NO_API_CHECK,

    /**
     * 不检查登录
     */
    NO_LOGIN_CHECK;
}
