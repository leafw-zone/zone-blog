package cn.leafw.zone.blog.web.controller;

import cn.leafw.zone.blog.api.dto.CategoryDto;
import cn.leafw.zone.blog.api.dto.CategoryQueryDto;
import cn.leafw.zone.blog.api.service.CategoryService;
import cn.leafw.zone.common.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author CareyWYR
 * @description
 * @date 2018/7/10 15:38
 */
@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/queryCategoryList",method = RequestMethod.POST)
    public ResponseDto queryCategoryList(@RequestBody CategoryQueryDto categoryQueryDto, HttpServletRequest request){
        String userId = request.getParameter("userId");
        categoryQueryDto.setAuthorId(userId);
        List<CategoryDto> categoryDtoList = categoryService.queryCategoryList(categoryQueryDto);
        return ResponseDto.instance(categoryDtoList);
    }

    @RequestMapping(value = "/saveCategory",method = RequestMethod.POST)
    public ResponseDto saveCategory(@RequestBody CategoryDto categoryDto, HttpServletRequest request){
        String userId = request.getParameter("userId");
        categoryDto.setAuthorId(userId);
        categoryService.saveCategory(categoryDto);
        return ResponseDto.instance(categoryDto);
    }

    @RequestMapping(value = "/deleteCategory",method = RequestMethod.POST)
    public ResponseDto deleteCategory(@RequestBody CategoryDto categoryDto, HttpServletRequest request){
        String userId = request.getParameter("userId");
        categoryDto.setAuthorId(userId);
        categoryService.deleteCategory(categoryDto);
        return ResponseDto.instance(categoryDto);
    }
}
