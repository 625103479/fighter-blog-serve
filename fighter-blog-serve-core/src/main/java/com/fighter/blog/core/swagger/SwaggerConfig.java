package com.fighter.blog.core.swagger;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.fighter.blog.core.entity.properties.FighterBlogProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractRefreshableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import springfox.documentation.oas.annotations.EnableOpenApi;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @BelongsProject: fighter-blog-serve
 * @BeloongsPackage: com.fighter.blog.core.swagger
 * @ClassName: SwaggerConfig
 * @CreateTime: 2021/12/20 0:10
 * @author: jie.zhang
 * @Email: 625103479@qq.com
 * @Desc:
 */
@EnableOpenApi
@Configuration
@EnableConfigurationProperties({FighterBlogProperties.class})
@ConditionalOnProperty(
        value = {"fighter.swagger.enable-swagger"},
        havingValue = "true",
        matchIfMissing = true
)
public class SwaggerConfig implements ApplicationContextAware {

    private ConfigurableListableBeanFactory beanFactory;

    @Resource
    private FighterBlogProperties fighterBlogProperties;

    @PostConstruct
    public void initSwagger() {
        SwaggerProperties swagger = fighterBlogProperties.getSwagger();
        Optional.ofNullable(swagger).ifPresent(swaggerProperties -> {
            List<SwaggerDocketProperties> apis = swaggerProperties.getApis();
            if (CollectionUtils.isEmpty(apis)) {
                return;
            }
            AtomicInteger swaggerDocketsIndex = new AtomicInteger(1);
            apis.forEach(apiDocketProperties -> {
                beanFactory.registerSingleton(String.format("swaggerDocket_%s", swaggerDocketsIndex.getAndIncrement()), apiDocketProperties.toSwaggerDocket());
            });

        });
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (applicationContext instanceof AbstractRefreshableApplicationContext) {
            this.beanFactory = ((AbstractRefreshableApplicationContext) applicationContext).getBeanFactory();
        } else {
            this.beanFactory = ((GenericApplicationContext) applicationContext).getBeanFactory();
        }
    }
}
