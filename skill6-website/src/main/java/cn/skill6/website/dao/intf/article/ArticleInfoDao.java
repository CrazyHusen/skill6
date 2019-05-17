package cn.skill6.website.dao.intf.article;

import java.util.List;

import cn.skill6.common.entity.po.article.ArticleInfo;

/**
 * 文章信息微服务接口
 *
 * @author 何明胜
 * @version 1.2
 * @since 2018年8月16日 下午10:28:04
 */
public interface ArticleInfoDao {

  int deleteByPrimaryKey(Long articleId);

  Long addArticleInfo(ArticleInfo articleInfo);

  ArticleInfo findByArticleId(Long articleId);

  List<ArticleInfo> findAll();

  void modifyByArticleId(ArticleInfo articleInfo);

  List<ArticleInfo> findByParams(ArticleInfo articleInfo);
}
