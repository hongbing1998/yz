package org.edu.cdtu.yz.bean;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author wencheng
 * @since 2019-06-27
 */
@TableName("tb_permission")
@Data
public class Permission extends Model<Permission> {

    private static final long serialVersionUID = 1L;

    private String id;
    @TableField("father_permission")
    private String fatherPermission;
    @TableField("permission_name")
    private String permissionName;
    @TableField("permission_url")
    private String permissionUrl;
    @TableField("permission_describe")
    private String permissionDescribe;
    private BigDecimal type;
    private String icon;
    private String perms;
    @TableField(exist = false)
    private Boolean choice;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
