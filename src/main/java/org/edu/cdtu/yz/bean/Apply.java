package org.edu.cdtu.yz.bean;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wencheng
 * @since 2019-06-27
 */
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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getResumeUrl() {
        return resumeUrl;
    }

    public void setResumeUrl(String resumeUrl) {
        this.resumeUrl = resumeUrl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getEpmloymentId() {
        return epmloymentId;
    }

    public void setEpmloymentId(String epmloymentId) {
        this.epmloymentId = epmloymentId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Apply{" +
        ", id=" + id +
        ", applyDate=" + applyDate +
        ", userId=" + userId +
        ", resumeUrl=" + resumeUrl +
        ", status=" + status +
        ", epmloymentId=" + epmloymentId +
        "}";
    }
}
