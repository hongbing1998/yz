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
@TableName("tb_employment")
public class Employment extends Model<Employment> {

    private static final long serialVersionUID = 1L;

    /**
     * 招聘信息id
     */
    private String id;
    /**
     * 招聘信息标题
     */
    private String title;
    /**
     * 招聘信息内容
     */
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
