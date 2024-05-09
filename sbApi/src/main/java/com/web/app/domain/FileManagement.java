package com.web.app.domain;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel // 声明当前对象是用来封装数据的对象
@Data
public class FileManagement implements Serializable {
    private static final long serialVersionUID = 1L;
    /***/
    @ApiModelProperty(name = "id", value = "主键", dataType = "Integer", required = true)
    private Long id;
    /** 文件名称 */
    @ApiModelProperty(name = "name", value = "文件名", dataType = "String", required = true)
    private String name;
    /** 文件路径 */
    private String filePath;
    /** 文件格式 */
    private String suffixName;
    /** 文件名称 */
    private String fileName;
    /** 文件类型 */
    private String fileType;

    public void setId(Long id) {
        this.id = id;
    }

}
