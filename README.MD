## MyBatis-x2x

基于Annotation注解的 一对多, 多对多 关联映射

SpringBoot

### 一对多

#### 表

student(id, name, classes_id)

classes(id, name)

#### 实体类
student(Integer id, String name,  Classes classes)

classes(Integer id, String name , List<Student> studentList)

#### 关系

一个学生在一个班中

一个班中可以有多个学生

#### Dao

student
```java

    @Select("select id, name, classes_id cid from student where id = #{id}")
    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(property = "classes", column = "cid", javaType = Classes.class, one = @One(select = "cn.chennan.mybatis.dao.ClassesDao.findById"))
            //@Result(property = "classes", column = "classes_id", javaType = Classes.class, one = @One(select = "cn.chennan.mybatis.dao.ClassesDao.findById"))
    })
    public Student findById(@Param("id") int id);

    @Select("select * from student where classes_id = #{classesId}")
    public Student findByClassesId(@Param("classesId") int classesId);
```

classes
```java
    @Select("select * from classes where id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "studentList", column = "id", javaType = List.class, many = @Many(select = "cn.chennan.mybatis.dao.StudentDao.findByClassesId"))
    })
    public Classes findById(@Param("id") int id);
```

#### 流程

studentDao.findById -@One-> classesDao.findById -@Many-> studentDao.findByClassesId
```java
        /*Student(id=1, name=cn1, classes=Classes(id=11, name=计科161, studentList=[Student(id=1, name=cn1, classes=null), Student(id=3, name=cn3, classes=null), Student(id=4, name=cn4, classes=null)]))*/
        Student st = studentDao.findById(1);
        System.out.println(st);
```

classesDao.findById -@Many-> studentDao.findByClassId 
```java
        /*Classes(id=11, name=计科161, studentList=[Student(id=1, name=cn1, classes=null), Student(id=3, name=cn3, classes=null), Student(id=4, name=cn4, classes=null)])*/
        Classes c = classesDao.findById(11);
        System.out.println(c);
```


### 多对多

多对多实则就是 一对多 + 中间表

<hr/>

一对多: 先查到 Classes 的信息(有ClassesDao负责), 在通过 classes.id去student表中 得到多行Student(由StudentRole返回lsit)

多对多: 先查到 user 的信息(由UserDao负责), 再通过user.id去user_role表中 得到多行role(由RoleDao负责返回list)