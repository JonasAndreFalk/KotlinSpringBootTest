package com.example.Test.service.impl

import com.example.Test.model.Journey
import com.example.Test.repository.JourneyRepository
import com.example.Test.service.JourneyService
import com.example.Test.service.exception.JourneyNotFoundException
import org.springframework.stereotype.Service
import kotlin.random.Random


@Service
class DefaultJourneyService(val journeyRepository:JourneyRepository):JourneyService {

    override fun getJourney(id: Long): Journey =
        journeyRepository.findById(id).orElseThrow { JourneyNotFoundException("Unable to find journey for id $id")
    }

    override fun getUserJourneys(userId : Long): List<Journey> =
        journeyRepository.findAll().filter { it.userId == userId }

    // Debugging
    override fun createRndJourneys(sampleSize: Int) {

        var repoInitSize = journeyRepository.count()
        var journeys : MutableList<Journey> = mutableListOf()

        while(journeys.size < sampleSize)
            journeys.add(Journey(repoInitSize+journeys.size, Random.nextLong(0,sampleSize.toLong())))

        journeyRepository.saveAll(journeys)

    }

    // Debugging
    override fun getAllJourneys(): List<Journey> = journeyRepository.findAll().toList()

}