package cn.chennan.mybatis.dao;

import cn.chennan.mybatis.po.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author ChenNan
 * @date 2019-11-21 下午6:22
 **/
@Mapper
public interface RoleDao {

    @Select("select * from role")
    public List<Role> listAll();

    @Select("select id, name from role where id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "users", column = "id", javaType = List.class, many = @Many(select = "cn.chennan.mybatis.dao.UserDao.findByRId"))
    })
    public Role findById(@Param("id") int id);

    // 在user_role表中 通过 用户id 得到 权限id --> 在role表中, 通过权限id 得到 权限信息
    @Select("select * from role where id in (select r_id from user_role where u_id = #{id})")
    public Role findByUId();
}
