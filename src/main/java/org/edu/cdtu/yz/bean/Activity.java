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
@TableName("tb_activity")
public class Activity extends Model<Activity> {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String titel;
    private String content;
    /**
     * 作者
     */
    @TableField("user_id")
    private String userId;
    @TableField("create_date")
    private String createDate;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
