package cn.leafw.zone.blog.web.controller;

import cn.leafw.zone.blog.api.dto.ArticleDto;
import cn.leafw.zone.blog.api.service.ArticleService;
import cn.leafw.zone.common.dto.ResponseDto;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CareyWYR
 * @description
 * @date 2018/7/10 15:38
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "test",method = RequestMethod.GET)
    public ResponseDto test(){
        return ResponseDto.instance(null);
    }

    @RequestMapping(value = "postArticle",method = RequestMethod.POST)
    public ResponseDto postArticle(@RequestBody ArticleDto articleDto){
        articleService.postArticle(articleDto);
        return ResponseDto.instance(articleDto);
    }
}
