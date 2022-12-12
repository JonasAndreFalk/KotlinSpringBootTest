package com.example.Test.repository

import com.example.Test.model.Journey
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface JourneyRepository : CrudRepository<Journey, Long>