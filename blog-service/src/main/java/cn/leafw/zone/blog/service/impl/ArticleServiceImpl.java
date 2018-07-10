package cn.leafw.zone.blog.service.impl;

import cn.leafw.zone.blog.api.service.ArticleService;
import cn.leafw.zone.blog.dao.repository.ArticleInfoRepository;
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

}
