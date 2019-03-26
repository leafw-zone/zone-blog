package cn.leafw.zone.blog.web.controller;

import cn.leafw.zone.blog.api.dto.ArticleDto;
import cn.leafw.zone.blog.api.dto.ArticleQueryDto;
import cn.leafw.zone.blog.api.service.ArticleService;
import cn.leafw.zone.common.dto.PagerResp;
import cn.leafw.zone.common.dto.ResponseDto;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author CareyWYR
 * @description
 * @date 2018/7/10 15:38
 */
@RestController
@RequestMapping("/article")
@Slf4j
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "postArticle",method = RequestMethod.POST)
    public ResponseDto postArticle(@RequestBody ArticleDto articleDto){
        articleService.postArticle(articleDto);
        return ResponseDto.instance(articleDto);
    }

    @RequestMapping(value = "/queryArticleList",method = RequestMethod.POST)
    public ResponseDto queryArticleList(@RequestBody ArticleQueryDto articleQueryDto, HttpServletRequest request){
        String userId = request.getParameter("userId");
        log.info("queryArticleList userId={}",userId);
        articleQueryDto.setAuthorId(userId);
        PagerResp<ArticleDto> pagerResp = articleService.queryArticleList(articleQueryDto);
        return ResponseDto.instance(pagerResp);
    }
}
