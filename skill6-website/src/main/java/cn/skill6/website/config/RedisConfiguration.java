package cn.skill6.website.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * redis配置
 *
 * @author 何明胜
 * @version 1.0
 * @since 2018年12月2日 下午10:36:15
 */
@Configuration
public class RedisConfiguration {

  /**
   * redisTemplate 序列化使用的jdkSerializeable, 存储二进制字节码, 自定义序列化类使用jackson
   *
   * @param redisConnectionFactory factory
   * @return redisTemplate
   */
  @Bean
  public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(factory);

    return redisTemplate;
  }

  /**
   * 对redis value类型数据操作
   *
   * @param redisTemplate
   * @return
   */
  @Bean
  public ValueOperations<String, Object> valueOperations(
      RedisTemplate<String, Object> redisTemplate) {
    return redisTemplate.opsForValue();
  }
}
