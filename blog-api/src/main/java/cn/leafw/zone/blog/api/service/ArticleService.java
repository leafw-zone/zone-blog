package cn.leafw.zone.blog.api.service;

import cn.leafw.zone.blog.api.dto.ArticleDto;
import cn.leafw.zone.blog.api.dto.ArticleQueryDto;
import cn.leafw.zone.common.dto.PagerResp;

/**
 * @author CareyWYR
 * @description
 * @date 2018/7/10 16:40
 */
public interface ArticleService {

    /**
     * 发布文章
     * @param articleDto
     */
    void postArticle(ArticleDto articleDto);

    /**
     * 分页查询文章列表
     * @param articleQueryDto
     * @return
     */
    PagerResp<ArticleDto> queryArticleList(ArticleQueryDto articleQueryDto);
}
