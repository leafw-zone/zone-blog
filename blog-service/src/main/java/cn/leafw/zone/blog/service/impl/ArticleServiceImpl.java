package cn.leafw.zone.blog.service.impl;

import cn.leafw.zone.blog.api.dto.ArticleDto;
import cn.leafw.zone.blog.api.service.ArticleService;
import cn.leafw.zone.blog.dao.entity.ArticleInfo;
import cn.leafw.zone.blog.dao.repository.ArticleInfoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author CareyWYR
 * @description
 * @date 2018/7/10 16:40
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleInfoRepository articleInfoRepository;

    public void postArticle(ArticleDto articleDto){
        ArticleInfo articleInfo = new ArticleInfo();
        BeanUtils.copyProperties(articleDto,articleInfo);
        articleInfoRepository.save(articleInfo);
    }

}
