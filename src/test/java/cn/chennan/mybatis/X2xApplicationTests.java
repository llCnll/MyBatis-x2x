package cn.chennan.mybatis;

import cn.chennan.mybatis.controller.IndexController;
import cn.chennan.mybatis.dao.ClassesDao;
import cn.chennan.mybatis.dao.RoleDao;
import cn.chennan.mybatis.dao.StudentDao;
import cn.chennan.mybatis.dao.UserDao;
import cn.chennan.mybatis.po.Classes;
import cn.chennan.mybatis.po.Role;
import cn.chennan.mybatis.po.Student;
import cn.chennan.mybatis.po.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class X2xApplicationTests {

    @Autowired
    IndexController indexController;
    @Autowired
    StudentDao studentDao;

    @Autowired
    ClassesDao classesDao;

    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao roleDao;

    @Test
    public void many2many(){

        List<User> users = userDao.listAll();
        users.forEach(user -> System.out.println(user));

        List<Role> roles = roleDao.listAll();
        roles.forEach(role -> System.out.println(role));
        System.out.println("========");

        User user = userDao.findById(1);
        System.out.println(user);

        Role role = roleDao.findById(1);
        System.out.println(role);
    }

    @Test
    public void one2many() {
        System.out.println("studentDao.listAll()");
        List<Student> students = studentDao.listAll();
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println("=======");
        List<Classes> classes = classesDao.listAll();
        classes.forEach(classes1 -> System.out.println(classes1));

        /*Student(id=1, name=cn1, classes=Classes(id=11, name=计科161, studentList=[Student(id=1, name=cn1, classes=null), Student(id=3, name=cn3, classes=null), Student(id=4, name=cn4, classes=null)]))*/
        // studentDao.findById --> classesDao.findById --> studentDao.findByClassesId 不存在循环引用
        Student st = studentDao.findById(1);
        System.out.println(st);

        /*Classes(id=11, name=计科161, studentList=[Student(id=1, name=cn1, classes=null), Student(id=3, name=cn3, classes=null), Student(id=4, name=cn4, classes=null)])*/
        // classesDao.findById --> studentDao.findByClassId 不存在循环引用
        Classes c = classesDao.findById(11);
        System.out.println(c);
    }

}

    /*SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for classes
-- ----------------------------
DROP TABLE IF EXISTS `classes`;
CREATE TABLE `classes`  (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of classes
-- ----------------------------
INSERT INTO `classes` VALUES (11, '计科161');
INSERT INTO `classes` VALUES (12, '计科161');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `classes_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, 'cn1', 11);
INSERT INTO `student` VALUES (2, 'cn2', 12);
INSERT INTO `student` VALUES (3, 'cn3', 11);
INSERT INTO `student` VALUES (4, 'cn4', 11);

SET FOREIGN_KEY_CHECKS = 1;*/