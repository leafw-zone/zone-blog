package cn.leafw.zone.blog.service.impl;

import cn.leafw.zone.blog.api.dto.TagDto;
import cn.leafw.zone.blog.api.dto.TagQueryDto;
import cn.leafw.zone.blog.api.service.TagService;
import cn.leafw.zone.blog.dao.entity.TagInfo;
import cn.leafw.zone.blog.dao.repository.TagInfoRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CareyWYR
 * @description
 * @date 2018/7/11 16:43
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagInfoRepository tagInfoRepository;

    @Override
    public List<TagDto> queryTagList(TagQueryDto tagQueryDto){
        List<TagDto> tagDtoList = new ArrayList<>();
        List<TagInfo> tagInfos = new ArrayList<>();
        if(null != tagQueryDto && StringUtils.isNotBlank(tagQueryDto.getTagName())){
            tagInfos = tagInfoRepository.findByTagName(tagQueryDto.getTagName());
        }else{
            tagInfos = tagInfoRepository.findAll();
        }
        for (TagInfo tagInfo : tagInfos) {
            TagDto tagDto = new TagDto();
            BeanUtils.copyProperties(tagInfo,tagDto);
            tagDtoList.add(tagDto);
        }
        return tagDtoList;
    }
}
