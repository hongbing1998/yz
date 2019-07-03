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
@TableName("tb_policy")
@Data
public class Policy extends Model<Policy> {

    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 政策标题
     */
    private String title;
    /**
     * 政策文件
     */
    private String address;
    @TableField("user_id")
    private String userId;
    @TableField("create_data")
    private String createData;
    @TableField("user_name")
    private String userName;
    @TableField("school_name")
    private String schoolName;
    @TableField("school_id")
    private String schoolId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
