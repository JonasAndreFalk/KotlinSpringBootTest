package com.example.Test.controller

import com.example.Test.model.Journey
import com.example.Test.service.JourneyService
import com.example.Test.service.exception.JourneysNotFoundException
import com.example.Test.service.exception.NotAllowedException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class JourneyController(val journeyService: JourneyService) {

    private var apiUserIdChecker: Long = 1

    @GetMapping("/journeys/{journeyId}")
    private fun getJourneyById(
    @RequestHeader("api-user-id") apiUserId: Long,
    @PathVariable journeyId: Long): Journey {

        if (apiUserId != apiUserIdChecker)
            throw NotAllowedException("api-user-id not allowed")

        return journeyService.getJourney(journeyId)

    }

    @GetMapping("/users/{userId}/journeys")
    @ResponseStatus(HttpStatus.OK)
    private fun getJourneysByUserId(
    @RequestHeader("api-user-id") apiUserId: Long,
    @PathVariable userId: Long): List<Journey> {

        if (apiUserId != apiUserIdChecker)
            throw NotAllowedException("api-user-id not allowed")

        val journeys = journeyService.getUserJourneys(userId)

        if (journeys.isEmpty())
            throw JourneysNotFoundException("No journeys found for userId $userId")

        return journeys
    }

    // Debugging
    @PostMapping("journeys/create/{sampleSize}")
    @ResponseStatus(HttpStatus.CREATED)
    private fun createRndJourneys(
    @RequestHeader("api-user-id") apiUserId: Long,
    @PathVariable sampleSize: Int) {

       if (apiUserId != apiUserIdChecker)
        throw NotAllowedException("api-user-id not allowed")

       journeyService.createRndJourneys(sampleSize)
    }

    // Debugging
    @GetMapping("/journeys")
    @ResponseStatus(HttpStatus.OK)
    private fun getAllJourneys(
    @RequestHeader("api-user-id") apiUserId: Long): List<Journey> {

        if (apiUserId != apiUserIdChecker)
            throw NotAllowedException("api-user-id not allowed")

        return journeyService.getAllJourneys()
    }


}