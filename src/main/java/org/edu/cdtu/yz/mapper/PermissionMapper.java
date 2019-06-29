package org.edu.cdtu.yz.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.edu.cdtu.yz.bean.Permission;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.edu.cdtu.yz.bean.Role;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wencheng
 * @since 2019-06-27
 */
public interface PermissionMapper extends BaseMapper<Permission> {
    @Results(id = "roleResult", value = {
            @Result(property = "fatherPermission", column = "father_permission"),
            @Result(property = "permissionUrl", column = "permission_url"),
            @Result(property = "permissionName", column = "permission_name")

    })
    @Select("select tb_permission.* from tb_permission,tb_permission_role,tb_role where tb_role.id=#{id} and tb_role.id=tb_permission_role.roleId and tb_permission_role.permissionId=tb_permission.id ")
    public List<Permission> selectPermissions(String id);
}
