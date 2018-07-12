package cn.leafw.zone.blog.web.controller;

import cn.leafw.zone.blog.api.dto.TagDto;
import cn.leafw.zone.blog.api.dto.TagQueryDto;
import cn.leafw.zone.blog.api.service.TagService;
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
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/queryTagList",method = RequestMethod.POST)
    public ResponseDto queryTagList(@RequestBody TagQueryDto tagQueryDto){
        List<TagDto> tagDtoList = tagService.queryTagList(tagQueryDto);
        return ResponseDto.instance(tagDtoList);
    }

    @RequestMapping(value = "/saveTag",method = RequestMethod.POST)
    public ResponseDto saveTag(@RequestBody TagDto tagDto){
        tagService.saveTag(tagDto);
        return ResponseDto.instance(tagDto);
    }
}
