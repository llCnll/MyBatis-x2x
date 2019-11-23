package cn.chennan.mybatis.dao;

import cn.chennan.mybatis.po.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 多对多 关系 简化成一对多即可
 * @author ChenNan
 * @date 2019-11-21 下午6:22
 **/
@Mapper
public interface UserDao {

    @Select("select * from user")
    public List<User> listAll();

    @Select("select u.id, u.name from user u where id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roles", column = "id", javaType = List.class, many = @Many(select = "cn.chennan.mybatis.dao.RoleDao.findByUId"))
    })
    public User findById(@Param("id") int id);

    // 在user_role表中 通过 权限id 得到 用户id, --> 在user表中 通过 用户id 得到 用户信息
    @Select("select * from user where id in (select u_id from user_role where r_id = #{id})")
    public User findByRId(@Param("id") int id);
}
