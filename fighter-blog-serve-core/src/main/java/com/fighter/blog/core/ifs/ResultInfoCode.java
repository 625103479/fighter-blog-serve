package com.fighter.blog.core.ifs;

/**
 * @BelongsProject: fighter-blog-serve
 * @BeloongsPackage: com.fighter.blog.core.ifs
 * @ClassName: ResultInfoCode
 * @CreateTime: 2021/12/20 0:22
 * @author: jie.zhang
 * @Email: 625103479@qq.com
 * @Desc: 返回状态码枚举定义
 */
public interface ResultInfoCode {

    /**
     * 返回状态码
     * 通用定义,如果由于
     *
     * @return 状态码
     */
    int getCode();

    /**
     * 返回提示信息
     *
     * @return 提示信息
     */
    String getMessage();
}
