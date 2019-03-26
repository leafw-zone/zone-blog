package cn.leafw.zone.blog.dao.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author CareyWYR
 * @description
 * @date 2018/7/10 16:36
 */
@Entity
@Table(name = "article_info")
@Data
public class ArticleInfo {
    @Id
    private String articleId;
    private String title;
    private String contentMd;
    private String contentHtml;
    private String authorId;
    private Date postTime;
    private String categories;
    private String tags;
    private String status;
    private String commentIds;
    private String isDeleted;
    private Date createTime;
    private String createBy;
    private Date updateTime;
    private String updateBy;
    private String isOpen;
    private String summary;

}
