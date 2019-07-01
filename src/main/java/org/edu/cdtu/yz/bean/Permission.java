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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFatherPermission() {
        return fatherPermission;
    }

    public void setFatherPermission(String fatherPermission) {
        this.fatherPermission = fatherPermission;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionUrl() {
        return permissionUrl;
    }

    public void setPermissionUrl(String permissionUrl) {
        this.permissionUrl = permissionUrl;
    }

    public String getPermissionDescribe() {
        return permissionDescribe;
    }

    public void setPermissionDescribe(String permissionDescribe) {
        this.permissionDescribe = permissionDescribe;
    }

    public BigDecimal getType() {
        return type;
    }

    public void setType(BigDecimal type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Permission{" +
        ", id=" + id +
        ", fatherPermission=" + fatherPermission +
        ", permissionName=" + permissionName +
        ", permissionUrl=" + permissionUrl +
        ", permissionDescribe=" + permissionDescribe +
        ", type=" + type +
        ", icon=" + icon +
        "}";
    }
}
