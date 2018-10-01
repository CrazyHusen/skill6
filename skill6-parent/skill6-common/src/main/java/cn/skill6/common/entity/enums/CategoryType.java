package cn.skill6.common.entity.enums;

import cn.skill6.common.entity.enums.intf.BaseEnum;

/**
 * 目录类型枚举，如文章、下载等
 *
 * @author 何明胜
 * @version 1.0
 * @since 2018年9月2日 下午7:20:58
 */
public enum CategoryType implements BaseEnum<Enum<CategoryType>, String> {
  ARTICLE("100", "目录类型为文章"),
  DISCUSS_AREA("200", "讨论区"),
  FILE_DOWNLOAD("300", "文章下载");

  private String stateCode;
  private String description;

  /**
   * @param stateCode
   * @param description
   */
  private CategoryType(String stateCode, String description) {
    this.stateCode = stateCode;
    this.description = description;
  }

  @Override
  public String getStateCode() {
    return stateCode;
  }

  @Override
  public String getDescrition() {
    return description;
  }
}
