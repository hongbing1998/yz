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
    private Long id;
    /**
     * 报名时间
     */
    @TableField("apply_date")
    private String applyDate;
    /**
     * 报名人id
     */
    @TableField("user_id")
    private String userId;
    /**
     * 简历路径
     */
    @TableField("resume_url")
    private String resumeUrl;
    /**
     * 报名状态（1:投递成功 2：简历已被查看 3：已被录取 4：你不适合）
     */
    private Integer status;
    /**
     * 招聘信息  id
     */
    @TableField("epmloyment_id")
    private String epmloymentId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
