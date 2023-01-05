package com.example.Test.service.impl

import com.example.Test.Config.CacheUtil
import com.example.Test.model.Journey
import com.example.Test.repository.JourneyRepository
import com.example.Test.service.JourneyService
import com.example.Test.service.exception.JourneyNotFoundException
import org.springframework.stereotype.Service
import kotlin.random.Random


@Service
class DefaultJourneyService(val journeyRepository:JourneyRepository, val cacheUtil: CacheUtil):JourneyService {

    override fun getJourneyFromCache(id: Long): Journey? {
        return cacheUtil.GetJourneyFromRepo(id);
    }

    override fun getJourney(id: Long): Journey =
        journeyRepository.findById(id).orElseThrow { JourneyNotFoundException("Unable to find journey for id $id")
    }

    override fun getUserJourneysFromCache(userId : Long): List<Journey>{
        return cacheUtil.GetUserJourneysFromRepo(userId)
    }

    override fun getUserJourneys(userId : Long): List<Journey> =
        journeyRepository.findAll().filter { it.userId == userId }


    // For testing
    override fun createRndJourneys(sampleSize: Int) {

        val repoInitSize = journeyRepository.count()
        val journeys : MutableList<Journey> = mutableListOf()

        while(journeys.size < sampleSize)
            journeys.add(Journey(repoInitSize+journeys.size, Random.nextLong(0,sampleSize.toLong())))

        journeyRepository.saveAll(journeys)

    }

    // For testing
    override fun createRndJourneysInCache(sampleSize: Int) {

        val repoInitSize = cacheUtil.GetJourneyCacheSize()

        for (i in 0..sampleSize) {
            val _id =  (repoInitSize + i)
            cacheUtil.AddJourneyToRepo(
                journey = Journey(
                    (repoInitSize + i),
                    Random.nextLong(0, sampleSize.toLong())
                )
            );
        }
    }

    // For testing
    override fun getAllJourneys(): List<Journey> = cacheUtil.GetAllJourneysFromCache()

}