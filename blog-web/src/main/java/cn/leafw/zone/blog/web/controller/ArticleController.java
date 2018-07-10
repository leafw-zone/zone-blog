package cn.leafw.zone.blog.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CareyWYR
 * @description
 * @date 2018/7/10 15:38
 */
@RestController
@RequestMapping("article")
public class ArticleController {

    @RequestMapping(value = "test",method = RequestMethod.GET)
    public String test(){
        return "----";
    }
}
