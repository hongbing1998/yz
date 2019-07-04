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
@TableName("tb_apply")
public class Apply extends Model<Apply> {

    private static final long serialVersionUID = 1L;

    /**
     * 报名表id
     */
    private String id;
    /**
     * 报名时间
     */
    @TableField("applyDate")
    private String applyDate;
    /**
     * 报名人id
     */
    @TableField("name")
    private String name;
    /**
     * 电话
     */
    @TableField("telephone")
    private String telephone;
    /**
     * 招聘信息  id
     */
    @TableField("QQ")
    private String qq;
    @TableField("crouse")
    private String crouse;
    @TableField("school")
    private String school;
    @TableField("employ_id")
    private String employId;

    @TableField("employ_title")
    private String employTitle;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
