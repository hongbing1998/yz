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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        return "Employment{" +
        ", id=" + id +
        ", title=" + title +
        ", content=" + content +
        ", userId=" + userId +
        ", createDate=" + createDate +
        "}";
    }
}
