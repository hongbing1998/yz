package org.edu.cdtu.yz.bean;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author wencheng
 * @since 2019-06-27
 */
@TableName("tb_user")
@ApiModel
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    private String id;
    @ApiModelProperty(notes = "用户名")
    private String username;

    @ApiModelProperty(notes = "密码, 密码不能为空且不能为简单密码, 否则后端抛出异常")
    private String password;
    private String phone;
    private String qq;
    private String email;
    /**
     *  外键
     */
    @TableField("school_id")
    private String schoolId;
    /**
     * 虽然不满足三大范式 但是 这样避免了多表关联
     */
    @TableField("school_name")
    private String schoolName;
    private String gender;
    private String birthdate;
    private String address;
    @TableField("create_date")
    private String createDate;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
        return "User{" +
        ", id=" + id +
        ", username=" + username +
        ", password=" + password +
        ", phone=" + phone +
        ", qq=" + qq +
        ", email=" + email +
        ", schoolId=" + schoolId +
        ", schoolName=" + schoolName +
        ", gender=" + gender +
        ", birthdate=" + birthdate +
        ", address=" + address +
        ", createDate=" + createDate +
        "}";
    }
}
