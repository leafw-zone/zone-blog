package cn.leafw.zone.blog.dao.repository;

import cn.leafw.zone.blog.dao.entity.TagInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author CareyWYR
 * @description
 * @date 2018/7/11 16:31
 */
@Repository
public interface TagInfoRepository extends JpaRepository<TagInfo,String>{

    List<TagInfo> findByTagNameAndAuthorIdAndIsDeleted(String tagName, String authorId, String isDeleted);

    List<TagInfo> findByAuthorIdAndIsDeleted(String authorId, String isDeleted);
}
