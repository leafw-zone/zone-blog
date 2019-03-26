package cn.leafw.zone.blog.service.impl;

import cn.leafw.zone.blog.api.dto.ArticleDto;
import cn.leafw.zone.blog.api.dto.ArticleQueryDto;
import cn.leafw.zone.blog.api.service.ArticleService;
import cn.leafw.zone.blog.api.utils.MarkdownUtil;
import cn.leafw.zone.blog.dao.entity.ArticleInfo;
import cn.leafw.zone.blog.dao.entity.CategoryInfo;
import cn.leafw.zone.blog.dao.entity.TagInfo;
import cn.leafw.zone.blog.dao.repository.ArticleInfoRepository;
import cn.leafw.zone.blog.dao.repository.CategoryInfoRepository;
import cn.leafw.zone.blog.dao.repository.TagInfoRepository;
import cn.leafw.zone.common.dto.PagerResp;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Autowired
    private CategoryInfoRepository categoryInfoRepository;
    @Autowired
    private TagInfoRepository tagInfoRepository;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

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
        //生成主键
        if(StringUtils.isEmpty(articleDto.getArticleId())){
            Long number = redisTemplate.opsForValue().increment("articleInfo_key",1L);
            String articleId = "ZNCE" + String.format("%09d", number);
            articleInfo.setArticleId(articleId);
        }
        //作者id TODO
        articleInfo.setAuthorId("1002");

        String contentHtml = MarkdownUtil.md2Html(articleInfo.getContentMd());
        articleInfo.setContentHtml(contentHtml);
        String summary = contentHtml.substring(contentHtml.indexOf(">")+1,contentHtml.indexOf("<",5));
        articleInfo.setSummary(summary);
        //状态为已发布
        articleInfo.setStatus("1");
        articleInfo.setIsDeleted("0");
        articleInfo.setCreateTime(new Date());
        articleInfo.setUpdateTime(new Date());
        articleInfo.setPostTime(new Date());
        articleInfoRepository.save(articleInfo);
    }

    @Override
    public PagerResp<ArticleDto> queryArticleList(ArticleQueryDto articleQueryDto){
        Pageable pageable = PageRequest.of(articleQueryDto.getPageNumber() - 1, articleQueryDto.getPageSize());
        Page<ArticleInfo> articleInfoPage = articleInfoRepository.findAll(new Specification<ArticleInfo>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<ArticleInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                if(null != articleQueryDto){
                    if(StringUtils.isNotBlank(articleQueryDto.getTitle())){
                        list.add(criteriaBuilder.like(root.get("title").as(String.class),"%"+ articleQueryDto.getTitle() + "%"));
                    }
                    if(StringUtils.isNotBlank(articleQueryDto.getCategories())){
                        list.add(criteriaBuilder.like(root.get("categories").as(String.class),"%"+ articleQueryDto.getCategories() + "%"));
                    }
                    if(StringUtils.isNotBlank(articleQueryDto.getTags())){
                        list.add(criteriaBuilder.like(root.get("tags").as(String.class),"%"+ articleQueryDto.getTags() + "%"));
                    }
                    if(StringUtils.isNotBlank(articleQueryDto.getAuthorId())){
                        list.add(criteriaBuilder.equal(root.get("authorId").as(String.class),articleQueryDto.getAuthorId()));
                    }
                }

                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        },pageable);

        List<ArticleDto> articleDtoList = new ArrayList<>();
        for (ArticleInfo articleInfo : articleInfoPage.getContent()) {
            ArticleDto articleDto = new ArticleDto();
            BeanUtils.copyProperties(articleInfo,articleDto);
            String categories = articleDto.getCategories();
            List<String> categoryNameList = new ArrayList<>();
            String tags = articleDto.getTags();
            List<String> tagNameList = new ArrayList<>();

            String[] categoryList =  categories.split(",");
            for (String categoryId : categoryList) {
                CategoryInfo categoryInfo = categoryInfoRepository.getOne(categoryId);
                if(null == categoryInfo){
                    break;
                }
                categoryNameList.add(categoryInfo.getCategoryName());
            }
            String[] tagList = tags.split(",");
            for (String tagId : tagList) {
                TagInfo tagInfo = tagInfoRepository.getOne(tagId);
                if(null == tagInfo){
                    break;
                }
                tagNameList.add(tagInfo.getTagName());
            }
            articleDto.setCategoriesName(String.join(",",categoryNameList));
            articleDto.setTagsName(String.join(",",tagNameList));
            articleDtoList.add(articleDto);
        }

        return new PagerResp<>(articleInfoPage.getTotalPages(),articleQueryDto.getPageNumber(),articleQueryDto.getPageSize(),articleDtoList);
    }

}
