package com.example.Test.service.exception

import java.lang.Exception

class JourneyNotFoundException(override val message:String) : Exception(message)