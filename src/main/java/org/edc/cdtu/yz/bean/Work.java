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
@TableName("tb_work")
@Data
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
