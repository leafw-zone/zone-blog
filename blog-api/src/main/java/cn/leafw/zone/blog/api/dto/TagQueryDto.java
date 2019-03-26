package cn.leafw.zone.blog.api.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author CareyWYR
 * @description
 * @date 2018/7/11 16:27
 */
@Data
public class TagQueryDto {

    private String tagId;

    private String tagName;

    private String isDeleted;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    private String authorId;
}
