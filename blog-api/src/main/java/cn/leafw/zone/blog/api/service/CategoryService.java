package cn.leafw.zone.blog.api.service;

import cn.leafw.zone.blog.api.dto.CategoryDto;
import cn.leafw.zone.blog.api.dto.CategoryQueryDto;

import java.util.List;

/**
 * @author CareyWYR
 * @description
 * @date 2018/7/11 16:33
 */
public interface CategoryService {

    List<CategoryDto> queryCategoryList(CategoryQueryDto categoryQueryDto);

    void saveCategory(CategoryDto categoryDto);
}
