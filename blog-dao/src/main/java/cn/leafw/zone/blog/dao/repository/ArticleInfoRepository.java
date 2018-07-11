package cn.leafw.zone.blog.dao.repository;

import cn.leafw.zone.blog.dao.entity.ArticleInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author CareyWYR
 * @description
 * @date 2018/7/10 16:41
 */
@Repository
public interface ArticleInfoRepository extends JpaRepository<ArticleInfo,String>,JpaSpecificationExecutor<ArticleInfo> {
}
