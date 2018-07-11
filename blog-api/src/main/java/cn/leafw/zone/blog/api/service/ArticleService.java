package cn.leafw.zone.blog.api.service;

import cn.leafw.zone.blog.api.dto.ArticleDto;

/**
 * @author CareyWYR
 * @description
 * @date 2018/7/10 16:40
 */
public interface ArticleService {

    void postArticle(ArticleDto articleDto);
}
