package com.sxgy.sp52.core.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Database Table Remarks:
 * 角色模块权限联系表
 * <p>
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table sp52_role_modular
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Sp52RoleModular角色模块表")
public class Sp52RoleModular implements Serializable {
    /**
     * Database Column Remarks:
     * 角色-模块表id
     */
    @ApiModelProperty(name = "id", value = "角色-模块表主键", dataType = "Long")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * Database Column Remarks:
     * 角色id
     */
    @ApiModelProperty(name = "roleId", value = "外键-角色表主键", dataType = "Long")
    private Long roleId;

    /**
     * Database Column Remarks:
     * 模块id
     */
    @ApiModelProperty(name = "modularId", value = "外键-模块表主键", dataType = "Long")
    private Long modularId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sp52_role_modular
     *
     * @mbg.generated Sat Nov 23 14:49:40 CST 2019
     */
    private static final long serialVersionUID = 1L;
}