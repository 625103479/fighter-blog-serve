package com.fighter.blog.core.entity.condition;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.base.CaseFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @BelongsProject: fighter-blog-serve
 * @BeloongsPackage: com.fighter.blog.core.entity.condition
 * @ClassName: ListCondition
 * @CreateTime: 2021/12/20 0:29
 * @author: jie.zhang
 * @Email: 625103479@qq.com
 * @Desc: 查询List数据时 通用查询条件包装
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "List查询条件")
public class ListCondition<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "查询用到的实体")
    @NotNull(message = "查询实体不能为空")
    private T entity;

    @ApiModelProperty(value = "查询字段")
    private List<String> filedList;

    @ApiModelProperty(value = "排序方式")
    private List<OrderItem> orderList;

    /**
     * 设置查询排序
     *
     * @param wrapper AbstractWrapper
     * @return ListCondition<T>
     */
    public ListCondition<T> addOrderList(AbstractWrapper wrapper) {
        if (Objects.isNull(wrapper) || CollectionUtils.isEmpty(orderList)) {
            return this;
        }
        // TODO: SQL注入屏蔽
        orderList.forEach(orderItem -> {
            if (Objects.nonNull(orderItem) && StringUtils.isNotBlank(orderItem.getColumn())) {
                wrapper.orderBy(
                        true,
                        orderItem.isAsc(),
                        CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, orderItem.getColumn())
                );
            }
        });


        return this;
    }

    /**
     * 设置查询字段
     *
     * @param queryWrapper QueryWrapper
     * @return ListCondition<T>
     */
    public ListCondition<T> addFiledList(QueryWrapper queryWrapper) {
        if (CollectionUtils.isNotEmpty(orderList) && Objects.isNull(queryWrapper)) {
            // TODO: SQL注入屏蔽
            queryWrapper.select(filedList.stream()
                    .filter(StringUtils::isNotBlank)
                    .map(item -> CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, item))
                    .toArray(String[]::new)
            );
        }
        return this;
    }

    /**
     * 设置字段以及排序
     *
     * @param wrapper AbstractWrapper
     */
    public void addOrderAndField(AbstractWrapper wrapper) {
        addOrderList(wrapper);
        if (wrapper instanceof QueryWrapper) {
            addFiledList((QueryWrapper) wrapper);
        }
    }
}
