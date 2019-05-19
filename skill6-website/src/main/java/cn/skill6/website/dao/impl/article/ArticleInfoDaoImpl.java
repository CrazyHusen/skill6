package cn.skill6.website.dao.impl.article;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.skill6.common.BaseUtils;
import cn.skill6.common.entity.po.article.ArticleInfo;
import cn.skill6.website.dao.intf.article.ArticleInfoDao;
import cn.skill6.website.dao.mappers.article.ArticleInfoMapper;
import cn.skill6.website.util.sequence.SequenceManager;
import lombok.extern.slf4j.Slf4j;

/**
 * 文章信息操作实现类
 *
 * @author 何明胜
 * @version 1.8
 * @since 2018年8月16日 下午10:29:29
 */
@Slf4j
@Repository
public class ArticleInfoDaoImpl implements ArticleInfoDao {

  @Autowired private ArticleInfoMapper articleInfoMapper;

  @Override
  public int deleteByPrimaryKey(Long articleId) {
    log.warn("删除id为{}的用户", articleId);
    return articleInfoMapper.deleteByPrimaryKey(articleId);
  }

  @Override
  public Long addArticleInfo(ArticleInfo articleInfo) {
    // 设置分布式用户id
    Long articleId = articleInfo.getArticleId();
    if (articleId == null) {
      articleId = SequenceManager.getNextId();
      articleInfo.setArticleId(articleId);
    }

    // 设置创建日期和最后修改日期
    articleInfo.setArticleCreateTime(new Date());
    articleInfo.setArticleUpdateTime(new Date());
    // 默认不置顶、无附件
    articleInfo.setArtilcePlaceTop(false);
    articleInfo.setArticleAttachFile(false);
    // 初始化阅读为0
    articleInfo.setArticleReadTotal(0);
    // 默认文章有效
    articleInfo.setArticleValid(true);

    // 增加其他字段的校验
    articleInfoMapper.insert(articleInfo);

    log.info("增加文章成功,{}", articleInfo);

    return articleId;
  }

  @Override
  public ArticleInfo findByArticleId(Long articleId) {
    ArticleInfo articleInfo = articleInfoMapper.selectByPrimaryKey(articleId);

    log.info("找到id为{}的文章,{}", articleId, articleInfo);

    return articleInfo;
  }

  @Override
  public List<ArticleInfo> findAll() {
    List<ArticleInfo> articleInfos = articleInfoMapper.selectAll();

    log.info("找到所有文章, {}", articleInfos);

    return articleInfos;
  }

  @Override
  public void modifyByArticleId(ArticleInfo articleInfo) {
    ArticleInfo articleInfoNew = findByArticleId(articleInfo.getArticleId());

    if (BaseUtils.isNotEmpty(articleInfo.getArticleTitle())) {
      articleInfoNew.setArticleTitle(articleInfo.getArticleTitle());
    }
    if (BaseUtils.isNotEmpty(articleInfo.getArticleSummary())) {
      articleInfoNew.setArticleSummary(articleInfo.getArticleSummary());
    }
    if (BaseUtils.isNotEmpty(articleInfo.getArticleLabel())) {
      articleInfoNew.setArticleLabel(articleInfo.getArticleLabel());
    }
    if (BaseUtils.isNotEmpty(articleInfo.getArticleHtmlContent())) {
      articleInfoNew.setArticleHtmlContent(articleInfo.getArticleHtmlContent());
    }
    if (BaseUtils.isNotEmpty(articleInfo.getArticleMdContent())) {
      articleInfoNew.setArticleMdContent(articleInfo.getArticleMdContent());
    }
    if (articleInfo.getCategoryId() != null) {
      articleInfoNew.setCategoryId(articleInfo.getCategoryId());
    }
    Boolean artilcePlaceTop = articleInfo.getArtilcePlaceTop();
    if (artilcePlaceTop != null && artilcePlaceTop == true) {
      articleInfoNew.setArtilcePlaceTop(true);
      articleInfoNew.setArticleTopPriority(articleInfo.getArticleTopPriority());
    }
    Boolean articleAttachFile = articleInfo.getArticleAttachFile();
    if (articleAttachFile != null && articleAttachFile == true) {
      articleInfoNew.setArticleAttachFile(true);
      articleInfoNew.setArticleAttachUrl(articleInfo.getArticleAttachUrl());
    }

    articleInfoMapper.updateByPrimaryKey(articleInfoNew);

    log.info("成功修改id为{}的文章内容", articleInfo.getArticleId());
  }

  @Override
  public List<ArticleInfo> findByParams(ArticleInfo articleInfo) {
    // 设置分页数据
    Page<ArticleInfo> page =
        PageHelper.startPage(articleInfo.getPageNum(), articleInfo.getPageSize());

    List<ArticleInfo> articleInfos = articleInfoMapper.selectByParams(articleInfo);
    log.info("找到文章数量：{}, 所有文章数量为：{}", articleInfos.size(), page.getTotal());

    return articleInfos;
  }
}
