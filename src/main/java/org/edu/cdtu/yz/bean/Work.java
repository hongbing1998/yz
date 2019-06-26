package org.edu.cdtu.yz.bean;

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
 * @since 2019-06-26
 */
@Data
@TableName("tb_work")
public class Work extends Model<Work> {

    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private String content;
    private String userId;
    private String createDate;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
