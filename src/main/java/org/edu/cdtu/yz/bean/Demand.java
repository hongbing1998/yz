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
@TableName("tb_demand")
public class Demand extends Model<Demand> {
    private static final long serialVersionUID = 1L;
    private String id;
    private String title;
    private Integer level;
    @TableField("school_id")
    private String schoolId;
    @TableField("user_id")
    private String userId;
    @TableField("create_date")
    private String createDate;
    private String content;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
