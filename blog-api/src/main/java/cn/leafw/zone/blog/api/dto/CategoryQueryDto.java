package cn.leafw.zone.blog.api.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author CareyWYR
 * @description
 * @date 2018/7/11 16:27
 */
@Data
public class CategoryQueryDto {

    private String categoryId;

    private String categoryName;

    private String isDeleted;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    private String authorId;
}
