package cn.leafw.zone.blog.service.impl;

import cn.leafw.zone.blog.api.dto.CategoryDto;
import cn.leafw.zone.blog.api.dto.CategoryQueryDto;
import cn.leafw.zone.blog.api.service.CategoryService;
import cn.leafw.zone.blog.dao.entity.CategoryInfo;
import cn.leafw.zone.blog.dao.repository.CategoryInfoRepository;
import cn.leafw.zone.common.enums.IsDeletedEnum;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author CareyWYR
 * @description
 * @date 2018/7/11 16:35
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryInfoRepository categoryInfoRepository;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public List<CategoryDto> queryCategoryList(CategoryQueryDto categoryQueryDto){
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        List<CategoryInfo> categoryInfos = new ArrayList<>();
        if(null != categoryQueryDto && StringUtils.isNotBlank(categoryQueryDto.getCategoryName())){
            categoryInfos = categoryInfoRepository.findByCategoryNameAndAuthorIdAndIsDeleted(categoryQueryDto.getCategoryName(),categoryQueryDto.getAuthorId(), IsDeletedEnum.UNDELETE.getId());
        }else{
            categoryInfos = categoryInfoRepository.findByAuthorIdAndIsDeleted(categoryQueryDto.getAuthorId(), IsDeletedEnum.UNDELETE.getId());
        }
        for (CategoryInfo categoryInfo : categoryInfos) {
            CategoryDto categoryDto = new CategoryDto();
            BeanUtils.copyProperties(categoryInfo,categoryDto);
            categoryDtoList.add(categoryDto);
        }
        return categoryDtoList;
    }

    @Override
    public void saveCategory(CategoryDto categoryDto){
        if(null == categoryDto){
            throw new RuntimeException("分类数据为空！");
        }
        CategoryInfo categoryInfo = new CategoryInfo();
        BeanUtils.copyProperties(categoryDto,categoryInfo);
        if(StringUtils.isEmpty(categoryDto.getCategoryId())){
            Long number = redisTemplate.opsForValue().increment("categoryInfo_key",1L);
            String categoryId = "ZNCY" + String.format("%05d", number);
            categoryInfo.setCategoryId(categoryId);
        }
        categoryInfo.setIsDeleted(IsDeletedEnum.UNDELETE.getId());
        categoryInfo.setCreateTime(new Date());
        categoryInfo.setUpdateTime(new Date());
        categoryInfoRepository.save(categoryInfo);
    }

    @Override
    public void deleteCategory(CategoryDto categoryDto){
        log.info("删除分类目录,categoryDto={}",JSONObject.toJSONString(categoryDto));
        CategoryInfo categoryInfo = categoryInfoRepository.findById(categoryDto.getCategoryId()).get();
        if(null != categoryInfo){
            categoryInfo.setIsDeleted(IsDeletedEnum.DELETE.getId());
            categoryInfo.setUpdateTime(new Date());
            categoryInfo.setUpdateBy(categoryDto.getAuthorId());
        }
    }

}
