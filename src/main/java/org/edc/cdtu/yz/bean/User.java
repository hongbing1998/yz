package org.edc.cdtu.yz.bean;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wencheng
 * @since 2019-06-25
 */
@Data
@TableName("tb_user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    private String id;
    private String username;
    private String upwd;
    private String telphone;
    private String qq;
    private String email;
    /**
     *  外键
     */
    private String schoolId;
    /**
     * 虽然不满足三大范式 但是 这样避免了多表关联
     */
    private String schoolName;
    private String gender;
    private String birthdate;
    private String address;
    private String createDate;




    @Override
    protected Serializable pkVal() {
        return this.id;
    }


}
