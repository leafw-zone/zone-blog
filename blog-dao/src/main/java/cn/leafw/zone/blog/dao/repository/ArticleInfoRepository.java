package cn.leafw.zone.blog.dao.repository;

import cn.leafw.zone.blog.dao.entity.ArticleInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author CareyWYR
 * @description
 * @date 2018/7/10 16:41
 */
public interface ArticleInfoRepository extends JpaRepository<ArticleInfo,String>,JpaSpecificationExecutor<ArticleInfo> {
}
