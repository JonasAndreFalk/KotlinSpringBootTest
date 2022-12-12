package com.example.Test.service

import com.example.Test.model.Journey
import com.example.Test.service.exception.JourneyNotFoundException
import com.example.Test.service.exception.UserNotFoundException

interface JourneyService {

    @Throws(JourneyNotFoundException::class)
    fun getJourney(id: Long): Journey

    @Throws(UserNotFoundException::class)
    fun getUserJourneys(userId : Long): List<Journey>

    // Debugging
    fun createRndJourneys(sampleSize : Int)
    // Debugging
    fun getAllJourneys(): List<Journey>

}