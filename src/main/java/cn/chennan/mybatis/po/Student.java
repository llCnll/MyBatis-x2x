package cn.chennan.mybatis.po;

import lombok.Data;

/**
 * @author ChenNan
 * @date 2019-11-19 下午1:38
 **/
@Data
public class Student {
    private Integer id;
    private String name;
    private Classes classes;
}
