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
@TableName("tb_path")
@Data
public class Path extends Model<Path> {

    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private String path;
    @TableField("user_id")
    private String userId;
    @TableField("create_date")
    private String createDate;
    @TableField("user_name")
    private String userName;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
