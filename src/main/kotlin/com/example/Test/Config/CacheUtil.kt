package com.example.Test.Config

import com.example.Test.model.Journey
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.CacheManager
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service


@Service
class CacheUtil {

    @Autowired
    private lateinit var redisTemplate: RedisTemplate<Long, Long>

    @Autowired
    private lateinit var cacheManager: RedisCacheManager

    fun GetJourneyFromRepo(journeyId: Long) : Journey? {

        var journey: Journey? = null

        val entry = redisTemplate.opsForZSet().range(0,journeyId,journeyId)
        if (entry != null) {
            if (entry.size > 0)
            {
                val userId : Long? = redisTemplate.opsForZSet().range(0,journeyId,journeyId)?.elementAt(0)

                if (userId != null)
                    journey = Journey(journeyId,userId)
            }
        }

        return journey

    }

    fun AddJourneyToRepo(journey: Journey) {

        redisTemplate.opsForZSet().add(0,journey.id,journey.userId.toDouble())

    }

    fun GetUserJourneysFromRepo(userId: Long) : List<Journey> {

        val range = redisTemplate.opsForZSet().rangeByScore(0, (userId).toDouble(), (userId).toDouble())

        val journeys = mutableListOf<Journey>()
        if (range != null)
            for(entry in range)
                journeys.add(Journey(entry,userId))

        return journeys

    }

    // For testing
    fun GetAllJourneysFromCache() : List<Journey> {

       val range = redisTemplate.opsForZSet().rangeWithScores(0,0,9999999999)
       val journeys = mutableListOf<Journey>()

        if (range != null)
            for(entry in range)
            {
                val userId : Long? = entry.score?.toLong()
                val journeyId : Long? = entry.value
                if (userId != null && journeyId != null)
                    journeys.add(Journey(journeyId,userId))
            }

        return journeys

    }

    // For testing
    fun GetJourneyCacheSize() : Long {

        return redisTemplate.opsForZSet().size(0)!!

    }

}