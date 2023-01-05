package com.example.Test.Config

import com.example.Test.model.Journey
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig
import org.springframework.data.redis.cache.RedisCacheManager.RedisCacheManagerBuilder
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair
import java.time.Duration


@Configuration
@EnableCaching
class RedisConfig (val env: Environment){

    @Bean
    fun cacheConfiguration(): RedisCacheConfiguration? {
        return defaultCacheConfig()
            .entryTtl(Duration.ofMinutes(60))
            .disableCachingNullValues()
            .serializeValuesWith(SerializationPair.fromSerializer(GenericJackson2JsonRedisSerializer()))
    }

    @Bean
    fun redisCacheManagerBuilderCustomizer(): RedisCacheManagerBuilderCustomizer? {
        return RedisCacheManagerBuilderCustomizer { builder: RedisCacheManagerBuilder ->
            builder
                .withCacheConfiguration(
                    "journeysCache",
                    defaultCacheConfig()
                        .entryTtl(Duration.ofMinutes(10))
                )
        }

    }
    @Bean
    fun redisTemplate(): RedisTemplate<Long, Long> {

        val lettuceConnectionFactory : LettuceConnectionFactory = LettuceConnectionFactory(env.getProperty("spring.redis.host").toString(),
            env.getProperty("spring.redis.port")?.toInt() ?: 6379)

        lettuceConnectionFactory.afterPropertiesSet()

        val template = RedisTemplate<Long, Long>()
        template.setConnectionFactory(lettuceConnectionFactory)

        return template

    }


}