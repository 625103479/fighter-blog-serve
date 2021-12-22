package com.fighter.blog.core.api;

import com.fighter.blog.core.enums.ApiCheckType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @BelongsProject: fighter-blog-serve
 * @BeloongsPackage: com.fighter.blog.core.api
 * @ClassName: RestApi
 * @CreateTime: 2021/12/22 23:08
 * @author: jie.zhang
 * @Email: 625103479@qq.com
 * @Desc: rest-api
 */
@ApiModel(value = "RestApi对象", description = "rest 接口")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RestApi implements Serializable {
    private static final long serialVersionUID = 625103479L;

    @ApiModelProperty(value = "资源对象")
    private String path;

    @ApiModelProperty(value = "资源名称")
    private String name;

    @ApiModelProperty(value = "访问动作")
    private String action;

    @ApiModelProperty(value = "鉴权类型")
    private ApiCheckType authType;

    @ApiModelProperty(value = "下级权限")
    private List<RestApi> children;
}
