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
@TableName("tb_policy")
public class Policy extends Model<Policy> {

    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 政策标题
     */
    private String title;
    /**
     * 政策文件
     */
    private String address;
    @TableField("user_id")
    private String userId;
    @TableField("create_data")
    private String createData;


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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCreateData() {
        return createData;
    }

    public void setCreateData(String createData) {
        this.createData = createData;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Policy{" +
        ", id=" + id +
        ", title=" + title +
        ", address=" + address +
        ", userId=" + userId +
        ", createData=" + createData +
        "}";
    }
}
