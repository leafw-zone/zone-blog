package cn.leafw.zone.blog.api.service;

import cn.leafw.zone.blog.api.dto.TagDto;
import cn.leafw.zone.blog.api.dto.TagQueryDto;

import java.util.List;

/**
 * @author CareyWYR
 * @description
 * @date 2018/7/11 16:34
 */
public interface TagService {

    List<TagDto> queryTagList(TagQueryDto tagQueryDto);

    void saveTag(TagDto tagDto);
}
