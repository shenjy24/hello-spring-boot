package com.jonas.jpa.config;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author shenjy
 * @createTime 2023/7/27 14:25
 * @description
 */
@Configuration
@EnableCaching
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig {
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory factory) {
        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer();
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        // 对于普通K-V操作时，key采取的序列化策略
        template.setKeySerializer(new StringRedisSerializer());
        // value采取的序列化策略
        template.setValueSerializer(serializer);
        // 在hash数据结构中，hash-key的序列化策略
        template.setHashKeySerializer(serializer);
        // 在hash数据结构中，hash-key的序列化策略
        template.setHashValueSerializer(serializer);
        template.setConnectionFactory(factory);
        template.afterPropertiesSet();
        return template;
    }
}
