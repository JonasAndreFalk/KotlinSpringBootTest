package com.example.Test.service.exception

import java.lang.Exception

class JourneysNotFoundException(override val message:String) : Exception(message)