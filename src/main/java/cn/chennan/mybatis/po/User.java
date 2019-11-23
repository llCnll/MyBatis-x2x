package cn.chennan.mybatis.po;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author ChenNan
 * @date 2019-11-23 下午2:46
 **/
@Data
public class User {
    private Integer id;
    private String name;

    List<Role> roles;
}
