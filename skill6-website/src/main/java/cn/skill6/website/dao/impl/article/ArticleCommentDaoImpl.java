package cn.skill6.website.dao.impl.article;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.skill6.common.entity.po.article.ArticleComment;
import cn.skill6.common.exception.general.NullPointerException;
import cn.skill6.website.dao.intf.article.ArticleCommentDao;
import cn.skill6.website.dao.mappers.article.ArticleCommentMapper;
import cn.skill6.website.util.sequence.SequenceManager;
import lombok.extern.slf4j.Slf4j;

/**
 * 文章评论操作实现类
 *
 * @author 何明胜
 * @version 1.4
 * @since 2018年8月27日 下午11:47:52
 */
@Slf4j
@Service
public class ArticleCommentDaoImpl implements ArticleCommentDao {

  @Autowired private ArticleCommentMapper articleCommentMapper;

  @Override
  public int deleteByCommentId(Long commentId) {
    log.warn("删除id为{}的评论", commentId);
    return articleCommentMapper.deleteByPrimaryKey(commentId);
  }

  @Override
  public Long addArticleComment(ArticleComment articleComment) {
    // 设置分布id
    Long commentId = SequenceManager.getNextId();
    if (commentId != null) {
      throw new NullPointerException("获取的Id为空");
    }
    articleComment.setCommentId(commentId);

    articleComment.setCommentTime(new Date());
    articleComment.setCommentValid(true);

    articleCommentMapper.insert(articleComment);

    log.info("增加评论成功,{}", articleComment);

    return commentId;
  }

  @Override
  public ArticleComment findByCommentId(Long commentId) {
    ArticleComment articleComment = articleCommentMapper.selectByPrimaryKey(commentId);

    log.info("找到id为{}的评论,{}", commentId, articleComment);

    return articleComment;
  }

  @Override
  public List<ArticleComment> findAll() {
    List<ArticleComment> articleComments = articleCommentMapper.selectAll();

    log.info("找到所有评论,{}", articleComments);

    return articleComments;
  }

  @Override
  public int modifyByCommentId(ArticleComment articleComment) {
    // 暂不支持修改评论
    return 0;
  }
}
