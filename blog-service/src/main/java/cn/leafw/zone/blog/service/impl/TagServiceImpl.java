package cn.leafw.zone.blog.service.impl;

import cn.leafw.zone.blog.api.dto.TagDto;
import cn.leafw.zone.blog.api.dto.TagQueryDto;
import cn.leafw.zone.blog.api.service.TagService;
import cn.leafw.zone.blog.dao.entity.TagInfo;
import cn.leafw.zone.blog.dao.repository.TagInfoRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

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

    @Override
    public void saveTag(TagDto tagDto) {
        if(null == tagDto){
            throw new RuntimeException("标签数据为空！");
        }
        TagInfo tagInfo = new TagInfo();
        BeanUtils.copyProperties(tagDto,tagInfo);
        if(StringUtils.isEmpty(tagDto.getTagId())){
            Long number = redisTemplate.opsForValue().increment("tagInfo_key",1L);
            String tagId = "ZNTG" + String.format("%05d", number);
            tagInfo.setTagId(tagId);
        }
        tagInfo.setIsDeleted("0");
        tagInfo.setCreateTime(new Date());
        tagInfo.setUpdateTime(new Date());
        tagInfoRepository.save(tagInfo);
    }
}
