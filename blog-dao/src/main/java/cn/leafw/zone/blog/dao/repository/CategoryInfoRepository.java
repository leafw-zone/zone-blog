package cn.leafw.zone.blog.dao.repository;

import cn.leafw.zone.blog.dao.entity.CategoryInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author CareyWYR
 * @description
 * @date 2018/7/11 16:30
 */
@Repository
public interface CategoryInfoRepository extends JpaRepository<CategoryInfo,String> {

    List<CategoryInfo> findByCategoryNameAndAuthorIdAndIsDeleted(String categoryName, String authorId, String isDeleted);

    List<CategoryInfo> findByAuthorIdAndIsDeleted(String authorId, String isDeleted);

}
