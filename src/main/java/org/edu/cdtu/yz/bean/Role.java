package org.edu.cdtu.yz.bean;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wencheng
 * @since 2019-06-27
 */
@Data
@TableName("tb_role")
public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

    private String id;
    @TableField("role_name")
    private String roleName;
    @TableField(exist = false)
    private boolean choice;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
