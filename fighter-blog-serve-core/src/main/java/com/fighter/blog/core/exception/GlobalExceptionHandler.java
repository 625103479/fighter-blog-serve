package com.fighter.blog.core.exception;

import com.fighter.blog.core.entity.result.ResultInfo;
import com.fighter.blog.core.entity.result.ResultInfoBuilder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @BelongsProject: fighter-blog-serve
 * @BeloongsPackage: com.fighter.blog.core.exception
 * @ClassName: GlobalExceptionHandler
 * @CreateTime: 2021/12/20 22:39
 * @author: jie.zhang
 * @Email: 625103479@qq.com
 * @Desc:
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = FighterBlogRuntimeException.class)
    public ResultInfo<StackTraceElement[]> captureFighterBlogRuntimeException(FighterBlogRuntimeException fighterBlogRuntimeException) {
        return ResultInfoBuilder.failure(fighterBlogRuntimeException.getCode(), fighterBlogRuntimeException.getMessage(), fighterBlogRuntimeException.getStackTrace());
    }

}
