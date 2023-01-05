package com.example.Test.model

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash("JourneyModel")
data class Journey (@Id val id: Long, val userId: Long) {

}