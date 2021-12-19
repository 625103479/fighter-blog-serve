package com.fighter.blog.core.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @BelongsProject: fighter-blog-serve
 * @BeloongsPackage: com.fighter.blog.core.aop
 * @ClassName: RestControllerAop
 * @CreateTime: 2021/12/20 0:58
 * @author: jie.zhang
 * @Email: 625103479@qq.com
 * @Desc:
 */
// TODO: 该方法未完成
public class RestControllerAop {

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController) && execution(public * com.fighter..controller..*.*(..))")
    public void controllerPointcut() {
    }

    @Around("controllerPointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        return proceedingJoinPoint;
    }
}
