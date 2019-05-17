package cn.skill6.website.dao.mappers.article;

import cn.skill6.common.entity.po.article.ArticleReadRecord;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 文章阅读记录mapper
 *
 * @author 何明胜
 * @version 1.2
 * @since 2018年8月15日 下午11:39:38
 */
public interface ArticleReadRecordMapper {
  int deleteByPrimaryKey(
      @Param("articleId") Long articleId, @Param("articleDateDaily") Date articleDateDaily);

  int insert(ArticleReadRecord articleReadRecord);

  ArticleReadRecord selectByPrimaryKey(
      @Param("articleId") Long articleId, @Param("articleDateDaily") Date articleDateDaily);

  List<ArticleReadRecord> selectAll();

  int updateByPrimaryKey(ArticleReadRecord articleReadRecord);
}
