package cn.leafw.zone.blog.dao.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author CareyWYR
 * @description
 * @date 2018/7/11 16:27
 */
@Entity
@Table(name = "tag_info")
@Data
public class TagInfo {

    @Id
    @Column(name = "tag_id")
    private String tagId;

    @Column(name = "tag_name")
    private String tagName;

    @Column(name = "is_deleted")
    private String isDeleted;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "author_id")
    private String authorId;
}
