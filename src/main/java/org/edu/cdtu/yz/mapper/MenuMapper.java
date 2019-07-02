package org.edu.cdtu.yz.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface MenuMapper {
    @Results(id = "roleResult", value = {
            @Result(property = "parentId", column = "father_permission"),
            @Result(property = "menuId", column = "id"),
            @Result(property = "name", column = "permission_name"),
            @Result(property = "url", column = "permission_url")

    })
    @Select("select DISTINCT p.father_permission,p.id,p.permission_name,p.permission_url" +
            " from tb_permission as p,tb_role as r,tb_user as u,tb_role_user as ru," +
            "tb_permission_role as pr where" +
            "  u.id=#{id} and u.id=ru.user_id and ru.role_id=r.id and " +
            " r.id=pr.roleId and pr.permissionId=p.id ")
    public List<Map<String, Object>> getAllMenu(String id);


}

