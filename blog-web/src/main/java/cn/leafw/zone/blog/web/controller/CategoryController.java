package cn.leafw.zone.blog.web.controller;

import cn.leafw.zone.blog.api.dto.CategoryDto;
import cn.leafw.zone.blog.api.dto.CategoryQueryDto;
import cn.leafw.zone.blog.api.service.CategoryService;
import cn.leafw.zone.common.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author CareyWYR
 * @description
 * @date 2018/7/10 15:38
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/queryCategoryList",method = RequestMethod.POST)
    public ResponseDto queryCategoryList(@RequestBody CategoryQueryDto categoryQueryDto){
        List<CategoryDto> categoryDtoList = categoryService.queryCategoryList(categoryQueryDto);
        return ResponseDto.instance(categoryDtoList);
    }
}
