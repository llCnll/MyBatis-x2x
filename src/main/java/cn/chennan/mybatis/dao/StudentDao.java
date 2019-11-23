package cn.chennan.mybatis.dao;

import cn.chennan.mybatis.po.Classes;
import cn.chennan.mybatis.po.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author ChenNan
 * @date 2019-11-19 下午1:39
 **/

/**
 * column 数据库字段名 也就是当前查询字段所对应的名称 cn.chennan.mybatis.dao.StudentDao#findById(int) 可以用别名去修改
 * property 实体类属性名
 * jdbcType 数据库字段数据
 * id 是否为主键
 *
 *
 * long countByXxx()
 * int deleteByXxx()
 * int save()
 * List<xxx> listAll();
 * List<xxx> listByXxx();
 * xxx findByXxx();
 * int updateXxxAnddXxxByXxx()
 */
@Mapper
public interface StudentDao {
    @Select("select * from student")
    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(property = "classes", column = "classes_id", javaType = Classes.class, one = @One(select = "cn.chennan.mybatis.dao.ClassesDao.findById"))
    })
    public List<Student> listAll();

    //@Select("select * from student where id = #{id}")
    @Select("select id, name, classes_id cid from student where id = #{id}")
    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(property = "classes", column = "cid", javaType = Classes.class, one = @One(select = "cn.chennan.mybatis.dao.ClassesDao.findById"))
            //@Result(property = "classes", column = "classes_id", javaType = Classes.class, one = @One(select = "cn.chennan.mybatis.dao.ClassesDao.findById"))
    })
    public Student findById(@Param("id") int id);

    @Select("select * from student where classes_id = #{classesId}")
    public Student findByClassesId(@Param("classesId") int classesId);

}
