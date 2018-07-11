package cn.leafw.zone.blog.service.impl;

import cn.leafw.zone.blog.api.dto.ArticleDto;
import cn.leafw.zone.blog.api.service.ArticleService;
import cn.leafw.zone.blog.dao.entity.ArticleInfo;
import cn.leafw.zone.blog.dao.repository.ArticleInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author CareyWYR
 * @description
 * @date 2018/7/10 16:40
 */
@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleInfoRepository articleInfoRepository;

    @Override
    public void postArticle(ArticleDto articleDto){
        if(null == articleDto){
            log.error("文章信息为空！");
            throw new  RuntimeException("文章信息为空！");
        }
        ArticleInfo articleInfo = new ArticleInfo();
        BeanUtils.copyProperties(articleDto,articleInfo);
        //转换分类目录和标签列表，用逗号隔开
        if(null != articleDto.getCategoriesList() && articleDto.getCategoriesList().size() > 0){
            articleInfo.setCategories(String.join(",",articleDto.getCategoriesList()));
        }
        if(null != articleDto.getTagsList() && articleDto.getTagsList().size() > 0){
            articleInfo.setTags(String.join(",",articleDto.getTagsList()));
        }
        //生成主键 TODO
        articleInfo.setArticleId("1001");
        articleInfo.setAuthorId("1002");

        //状态为已发布
        articleInfo.setStatus("1");
        articleInfo.setIsDeleted("0");
        articleInfo.setCreateTime(new Date());
        articleInfo.setUpdateTime(new Date());
        articleInfoRepository.save(articleInfo);
    }

}
