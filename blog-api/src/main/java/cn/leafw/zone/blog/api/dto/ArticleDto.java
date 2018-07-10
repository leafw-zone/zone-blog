package cn.leafw.zone.blog.api.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author CareyWYR
 * @description
 * @date 2018/7/10 16:39
 */
@Data
public class ArticleDto {
    private String articleId;
    private String title;
    private String contentMd;
    private String contentHtml;
    private String authorId;
    private Timestamp postTime;
    private String categories;
    private String tags;
    private String status;
    private String commentIds;
    private String isDeleted;
    private Date createTime;
    private String createBy;
    private Date updateTime;
    private String updateBy;
}
