package cn.leafw.zone.blog.service.impl;

import cn.leafw.zone.blog.api.dto.CategoryDto;
import cn.leafw.zone.blog.api.dto.CategoryQueryDto;
import cn.leafw.zone.blog.api.service.CategoryService;
import cn.leafw.zone.blog.dao.entity.CategoryInfo;
import cn.leafw.zone.blog.dao.repository.CategoryInfoRepository;
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
            categoryInfos = categoryInfoRepository.findByCategoryName(categoryQueryDto.getCategoryName());
        }else{
            categoryInfos = categoryInfoRepository.findAll();
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
        categoryInfo.setIsDeleted("0");
        categoryInfo.setCreateTime(new Date());
        categoryInfo.setUpdateTime(new Date());
        categoryInfoRepository.save(categoryInfo);
    }

}
