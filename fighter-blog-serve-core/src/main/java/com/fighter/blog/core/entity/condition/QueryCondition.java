package com.fighter.blog.core.entity.condition;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

/**
 * @BelongsProject: fighter-blog-serve
 * @BeloongsPackage: com.fighter.blog.core.entity.condition
 * @ClassName: QueryCondition
 * @CreateTime: 2021/12/20 0:42
 * @author: jie.zhang
 * @Email: 625103479@qq.com
 * @Desc: 分页查询数据条件通用包装
 */
@ApiModel(description = "分页查询条件")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public final class QueryCondition<T> extends ListCondition<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "第几页")
    @Range(min = 1)
    private int pageNum;

    @ApiModelProperty(value = "页大小")
    @Range(min = 1, max = 500)
    private int pageSize;

    /**
     * 获取分页对象
     *
     * @return Page<T>
     */
    public Page<T> gainPage() {
        return new Page<T>(pageNum, pageSize);
    }
}
