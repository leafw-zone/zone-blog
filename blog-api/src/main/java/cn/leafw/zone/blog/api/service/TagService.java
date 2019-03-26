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

    /**
     * 查询所有tag
     * @param tagQueryDto
     * @return
     */
    List<TagDto> queryTagList(TagQueryDto tagQueryDto);

    /**
     * 保存tag
     * @param tagDto
     */
    void saveTag(TagDto tagDto);

    /**
     * 删除tag
     * @param tagDto
     */
    void deleteTag(TagDto tagDto);
}
