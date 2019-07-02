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
@TableName("tb_work")
@Data
public class Work extends Model<Work> {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private String content;
    @TableField("user_id")
    private String userId;
    @TableField("create_date")
    private String createDate;



    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
