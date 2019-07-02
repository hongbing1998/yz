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

    private Long id;
    @TableField("school_name")
    private String schoolName;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
