package com.fighter.blog.core.entity.result;

import com.fighter.blog.core.enums.SystemResultInfoCodeEnum;
import com.fighter.blog.core.ifs.ResultInfoCode;

/**
 * @BelongsProject: fighter-blog-serve
 * @BeloongsPackage: com.fighter.blog.core.entity.result
 * @ClassName: ResultInfoBuilder
 * @CreateTime: 2021/12/20 0:21
 * @author: jie.zhang
 * @Email: 625103479@qq.com
 * @Desc: 前端统一响应实体构建 Builder
 */
public class ResultInfoBuilder {
    private ResultInfoBuilder() {
    }

    public static <T> ResultInfo<T> success(int code, String message, T data) {
        return ResultInfo.<T>builder().data(data).message(message).code(code).build();
    }

    public static <T> ResultInfo<T> success(String message) {
        return success(SystemResultInfoCodeEnum.SUCCESS.getCode(), message, null);
    }

    public static <T> ResultInfo<T> success(String message, T data) {
        return success(SystemResultInfoCodeEnum.SUCCESS.getCode(), message, data);
    }

    public static <T> ResultInfo<T> failure(int code, String message, T data) {
        return ResultInfo.<T>builder().data(data).message(message).code(code).build();
    }

    public static <T> ResultInfo<T> failure(String message) {
        return failure(SystemResultInfoCodeEnum.CLIENT_ERROR.getCode(), message, null);
    }

    public static <T> ResultInfo<T> failure(String message, T data) {
        return failure(SystemResultInfoCodeEnum.CLIENT_ERROR.getCode(), message, data);
    }

    public static <T> ResultInfo<T> failure(int code, String message) {
        return failure(code, message, null);
    }

    public static <T> ResultInfo<T> build(ResultInfoCode resultInfoCode, T data) {
        return ResultInfo.<T>builder().message(resultInfoCode.getMessage()).code(resultInfoCode.getCode()).data(data).build();
    }
}
