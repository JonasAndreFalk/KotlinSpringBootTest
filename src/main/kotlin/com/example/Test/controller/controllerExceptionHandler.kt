package com.example.Test.controller

import com.example.Test.model.ErrorMessage
import com.example.Test.service.exception.JourneyNotFoundException
import com.example.Test.service.exception.JourneysNotFoundException
import com.example.Test.service.exception.NotAllowedException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class controllerExceptionHandler {

    @ExceptionHandler
    fun handleIllegalStateException(ex: IllegalStateException): ResponseEntity<ErrorMessage> {

        val errorMessage = ErrorMessage(
            HttpStatus.BAD_REQUEST.value(),
            ex.message
        )
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    fun handleJourneyNotFoundException(ex: JourneyNotFoundException): ResponseEntity<ErrorMessage> {

        val errorMessage = ErrorMessage(
            HttpStatus.NOT_FOUND.value(),
            ex.message
        )
        return ResponseEntity(errorMessage, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler
    fun handleNoJourneysFoundException(ex: JourneysNotFoundException): ResponseEntity<ErrorMessage> {

        val errorMessage = ErrorMessage(
            HttpStatus.NOT_FOUND.value(),
            ex.message
        )
        return ResponseEntity(errorMessage, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler
    fun handleNotAllowedException(ex: NotAllowedException): ResponseEntity<ErrorMessage> {

        val errorMessage = ErrorMessage(
            HttpStatus.UNAUTHORIZED.value(),
            ex.message
        )
        return ResponseEntity(errorMessage, HttpStatus.UNAUTHORIZED)
    }
}