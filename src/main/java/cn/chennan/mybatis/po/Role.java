package cn.chennan.mybatis.po;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author ChenNan
 * @date 2019-11-23 下午2:45
 **/
@Data
public class Role {
    private Integer id;
    private String name;

    private List<User> users;
}
