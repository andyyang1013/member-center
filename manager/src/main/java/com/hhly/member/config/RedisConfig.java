package com.hhly.member.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

/**
* redis配置
* @author jiasx
* @create 2017/12/1 10:10
**/
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    /**哨兵多节点分隔符*/
    private static String SPLIT_STR = ",";

    @Autowired
    RedisProperties redisProperties;

    @Bean
    public JedisPoolConfig getJedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(redisProperties.getPool().getMaxIdle());
        jedisPoolConfig.setMaxWaitMillis(redisProperties.getPool().getMaxWait());
        jedisPoolConfig.setMaxTotal(redisProperties.getPool().getMaxActive());
        jedisPoolConfig.setMinIdle(redisProperties.getPool().getMinIdle());
        return jedisPoolConfig;
    }

    @Bean
    public JedisSentinelPool jedisSentinelPool(){
        Set<String> sentinels = new HashSet<>();
        if (StringUtils.isNotEmpty(redisProperties.getSentinel().getNodes())) {
            for (String sentinel : redisProperties.getSentinel().getNodes().split(SPLIT_STR)) {
                sentinels.add(sentinel);
            }
        }
        return new JedisSentinelPool(redisProperties.getSentinel().getMaster(), sentinels, getJedisPoolConfig(), redisProperties.getTimeout(), redisProperties.getPassword(), redisProperties.getDatabase());
    }


//    @Bean
//    public JedisConnectionFactory redisConnectionFactory() {
//        JedisConnectionFactory factory = new JedisConnectionFactory();
//        factory.setHostName(redisProperties.getHost());
//        factory.setPort(redisProperties.getPort());
//        factory.setPassword(redisProperties.getPassword());
//        factory.setUsePool(true);
//        factory.setDatabase(redisProperties.getDatabase());
//        factory.setTimeout(redisProperties.getTimeout());
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMinIdle(redisProperties.getPool().getMinIdle());
//        jedisPoolConfig.setMaxIdle(redisProperties.getPool().getMaxIdle());
//        jedisPoolConfig.setMaxWaitMillis(redisProperties.getPool().getMaxWait());
//        factory.setPoolConfig(jedisPoolConfig);
//        return factory;
//    }
    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        cacheManager.setDefaultExpiration(30000);
        return cacheManager;
    }
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate redisTemplate = new StringRedisTemplate(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }


    @Value("${spring.session.redis.namespace}")
    private String namespace;

    /**
     * 自定义keyGenerator
     * @return
     */
    @Override
    @Bean
    public KeyGenerator keyGenerator() {
        return (target,method,params) -> {
            StringBuffer sb = new StringBuffer(namespace);
            sb.append(target.getClass().getSimpleName() + ":");
            sb.append(method.getName());
            if (params.length > 0) {
                sb.append(":");
                for (Object object : params) {
                    sb.append(object.toString());
                }
            }
            return sb.toString();
        };
    }
}
