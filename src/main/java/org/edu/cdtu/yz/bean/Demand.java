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
@TableName("tb_demand")
public class Demand extends Model<Demand> {

    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    /**
     * 1 :紧急 2非常紧急
     */
    private Integer level;
    /**
     * school 主键  
     */
    @TableField("school_id")
    private String schoolId;
    /**
     * 作者
     */
    @TableField("user_id")
    private String userId;
    @TableField("create_date")
    private String createDate;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Demand{" +
        ", id=" + id +
        ", title=" + title +
        ", level=" + level +
        ", schoolId=" + schoolId +
        ", userId=" + userId +
        ", createDate=" + createDate +
        "}";
    }
}
