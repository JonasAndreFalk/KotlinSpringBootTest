package com.example.Test.model

import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed

@RedisHash("JourneyModel")
data class Journey (@Indexed val id: Long, val userId: Long) {

}