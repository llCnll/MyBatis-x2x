package cn.chennan.mybatis.dao;

import cn.chennan.mybatis.po.Classes;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author ChenNan
 * @date 2019-11-21 下午6:22
 **/
@Mapper
public interface ClassesDao {

    @Select("select * from classes")
    public List<Classes> listAll();

    @Select("select * from classes where id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "studentList", column = "id", javaType = List.class, many = @Many(select = "cn.chennan.mybatis.dao.StudentDao.findByClassesId"))
    })
    public Classes findById(@Param("id") int id);
}
