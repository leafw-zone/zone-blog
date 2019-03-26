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

    /**
     * 查询所有分类
     * @param categoryQueryDto
     * @return
     */
    List<CategoryDto> queryCategoryList(CategoryQueryDto categoryQueryDto);

    /**
     * 保存分类
     * @param categoryDto
     */
    void saveCategory(CategoryDto categoryDto);

    /**
     * 删除分类目录
     * @param categoryDto
     */
    void deleteCategory(CategoryDto categoryDto);
}
