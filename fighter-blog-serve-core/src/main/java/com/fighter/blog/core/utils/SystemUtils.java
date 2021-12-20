package com.fighter.blog.core.utils;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fighter.blog.core.enums.SystemResultInfoCodeEnum;
import com.fighter.blog.core.exception.FighterBlogRuntimeException;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.springframework.beans.BeanUtils;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @BelongsProject: fighter-blog-serve
 * @BeloongsPackage: com.fighter.blog.core.utils
 * @ClassName: SystemUtils
 * @CreateTime: 2021/12/20 22:34
 * @author: jie.zhang
 * @Email: 625103479@qq.com
 * @Desc:
 */
public final class SystemUtils {

    private SystemUtils() {
    }

    /**
     * 生成UUID
     *
     * @return
     */
    public static String generateNewUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final Validator validator = Validation.byDefaultProvider().configure().messageInterpolator(new ResourceBundleMessageInterpolator(new PlatformResourceBundleLocator("ValidationMessages_zh_CN"))).buildValidatorFactory().getValidator();

    /**
     * 获取 ObjectMapper
     *
     * @return ObjectMapper
     */
    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    /**
     * 检验参数是否合法
     *
     * @param object 被校验对象
     * @param groups 校验分组
     * @param <T>    泛型
     */
    public static <T> void validBean(T object, Class<?>... groups) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(object, groups);
        if (constraintViolations.size() > 0) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }

    /**
     * 复制 bean
     *
     * @param poObj   被复制对象
     * @param voClass 目标对象类类型
     * @param <T>     目标对象类
     * @return 目标对象类
     */
    public static <T> T copyBean(Object poObj, final Class<T> voClass) {
        try {
            T voObj = voClass.newInstance();
            BeanUtils.copyProperties(poObj, voObj);
            return voObj;
        } catch (Exception e) {
            throw new FighterBlogRuntimeException(SystemResultInfoCodeEnum.SERVER_ERROR.getCode(), "bean转换失败");
        }
    }

    /**
     * 复制list
     *
     * @param source 被复制对象类List
     * @param target 目标对象类类型
     * @param <T>    被复制对象类
     * @param <K>    目标对象类
     * @return
     */
    public static <T, K> List<K> copyList(List<T> source, Class<K> target) {
        if (CollectionUtils.isEmpty(source)) {
            return Collections.emptyList();
        }
        return source.stream().map(e -> copyBean(e, target)).collect(Collectors.toList());
    }
}
