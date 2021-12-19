package com.fighter.blog.core.enums;

import com.fighter.blog.core.ifs.ResultInfoCode;
import lombok.Getter;

/**
 * @BelongsProject: fighter-blog-serve
 * @BeloongsPackage: com.fighter.blog.core.enums
 * @ClassName: SystemResultInfoCodeEnum
 * @CreateTime: 2021/12/20 0:23
 * @author: jie.zhang
 * @Email: 625103479@qq.com
 * @Desc: 系统基础返回状态码枚举
 */
@Getter
public enum SystemResultInfoCodeEnum implements ResultInfoCode {

    /**
     * 系统基础返回状态码枚举
     */
    // 请求成功
    SUCCESS(200, "请求成功"),
    // 客户端错误缺省值
    CLIENT_ERROR(400, "客户端错误"),
    // 服务端错误缺省值
    SERVER_ERROR(500, "服务端执行出错"),
    // 没有权限访问
    NO_PERMISSION(401, "没有%s权限");

    private final int code;

    private final String message;

    SystemResultInfoCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
