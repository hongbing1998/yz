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
@TableName("tb_school")
public class School extends Model<School> {
    private static final long serialVersionUID = 1L;
    private String id;
    @TableField("school_name")
    private String schoolName;
    @TableField("school_info")
    private String schoolInfo;
    @TableField("school_need_help")
    private Boolean schoolNeedHelp;
    @TableField("school_operate_id")
    private String schoolOperateId;
    @TableField("school_operate_name")
    private String schoolOperateName;
    @TableField("school_operate_time")
    private String schoolOperateTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
