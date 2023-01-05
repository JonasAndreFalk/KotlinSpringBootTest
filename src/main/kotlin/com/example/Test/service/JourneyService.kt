package com.example.Test.service

import com.example.Test.model.Journey
import com.example.Test.service.exception.JourneyNotFoundException
import com.example.Test.service.exception.UserNotFoundException

interface JourneyService {

    @Throws(JourneyNotFoundException::class)
    fun getJourneyFromCache(id: Long): Journey?

    @Throws(JourneyNotFoundException::class)
    fun getJourney(id: Long): Journey

    @Throws(UserNotFoundException::class)
    fun getUserJourneys(userId : Long): List<Journey>

    @Throws(UserNotFoundException::class)
    fun getUserJourneysFromCache(userId : Long): List<Journey>

    // For testing
    fun createRndJourneys(sampleSize : Int)

    // For testing
    fun createRndJourneysInCache(sampleSize : Int)

    // For testing
    fun getAllJourneys(): List<Journey>

}