package com.fighter.blog.core.exception;

import com.fighter.blog.core.ifs.ResultInfoCode;

/**
 * @BelongsProject: fighter-blog-serve
 * @BeloongsPackage: com.fighter.blog.core.exception
 * @ClassName: FighterBlogRuntimeException
 * @CreateTime: 2021/12/20 22:38
 * @author: jie.zhang
 * @Email: 625103479@qq.com
 * @Desc:
 */
public class FighterBlogRuntimeException extends RuntimeException {
    private int code;

    public FighterBlogRuntimeException() {
    }

    public FighterBlogRuntimeException(int code, String message) {
        super(message);
        this.code = code;
    }

    public FighterBlogRuntimeException(ResultInfoCode resultInfoCode) {
        super(resultInfoCode.getMessage());
        this.code = resultInfoCode.getCode();
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(final int code) {
        this.code = code;
    }
}
