package cn.chennan.mybatis.po;

import lombok.Data;

import java.util.List;

/**
 * @author ChenNan
 * @date 2019-11-19 下午1:38
 **/
@Data
public class Classes {
    private Integer id;
    private String name;

    private List<Student> studentList;
}
