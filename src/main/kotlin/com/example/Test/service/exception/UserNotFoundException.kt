package com.example.Test.service.exception

import java.lang.Exception

class UserNotFoundException(override val message:String) : Exception(message)