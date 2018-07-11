package cn.leafw.zone.blog.api.dto;

import cn.leafw.zone.common.dto.BaseQueryDto;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author CareyWYR
 * @description
 * @date 2018/7/10 16:39
 */
@Data
public class ArticleQueryDto extends BaseQueryDto{
    private String articleId;
    private String title;
    private String contentMd;
    private String contentHtml;
    private String authorId;
    private Timestamp postTime;
    private String categories;
    private String tags;
    private List<String> categoriesList;
    private List<String> tagsList;
    private String status;
    private String commentIds;
    private String isDeleted;
    private Date createTime;
    private String createBy;
    private Date updateTime;
    private String updateBy;
}
