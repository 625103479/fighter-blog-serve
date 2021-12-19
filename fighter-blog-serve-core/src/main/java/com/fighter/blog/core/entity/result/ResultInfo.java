package com.fighter.blog.core.entity.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @BelongsProject: fighter-blog-serve
 * @BeloongsPackage: com.fighter.blog.core.entity.result
 * @ClassName: ResultInfo
 * @CreateTime: 2021/12/20 0:18
 * @author: jie.zhang
 * @Email: 625103479@qq.com
 * @Desc:
 */
@Data
@Builder
@ApiModel(description = "Restful API 全局统一响应实体")
@NoArgsConstructor
@AllArgsConstructor
public final class ResultInfo<T> implements Serializable {

    private static final long serialVersionUID = 4359709211352400087L;

    @ApiModelProperty("响应业务状态码")
    private int code;

    @ApiModelProperty("返回的数据, 泛型, 请根据具体接口描述进行解析")
    private T data;

    @ApiModelProperty("成功或错误的提示信息")
    private String message;
}
