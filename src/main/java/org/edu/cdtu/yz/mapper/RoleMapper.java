package org.edu.cdtu.yz.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.edu.cdtu.yz.bean.Role;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wencheng
 * @since 2019-06-27
 */
public interface RoleMapper extends BaseMapper<Role> {
    @Results(id = "roleResult", value = {
            @Result(property = "roleName", column = "role_name")
    })

    @Select("select tb_role.* from tb_user,tb_role_user,tb_role where tb_user.id=#{id} and tb_user.id=tb_role_user.user_id and tb_role_user.role_id=tb_role.id  ")
    public List<Role> selectRoles(String id);
}
