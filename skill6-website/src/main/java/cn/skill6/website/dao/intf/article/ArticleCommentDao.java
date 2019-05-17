package cn.skill6.website.dao.intf.article;

import java.util.List;

import cn.skill6.common.entity.po.article.ArticleComment;

/**
 * 文章评论操作接口
 *
 * @author 何明胜
 * @version 1.3
 * @since 2018年8月24日 上午12:27:22
 */
public interface ArticleCommentDao {
  int deleteByCommentId(Long commentId);

  Long addArticleComment(ArticleComment articleComment);

  ArticleComment findByCommentId(Long commentId);

  List<ArticleComment> findAll();

  int modifyByCommentId(ArticleComment articleComment);
}
