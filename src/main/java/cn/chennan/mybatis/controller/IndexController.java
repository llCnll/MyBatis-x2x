package cn.chennan.mybatis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ChenNan
 * @date 2019-11-19 下午1:41
 **/
@Controller
@RequestMapping("index")
public class IndexController {

    @RequestMapping("/")
    @ResponseBody
    public String index(){
        System.out.println("index");
        return "{result:\"index\"}";
    }
}
