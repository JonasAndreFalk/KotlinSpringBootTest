package com.example.Test.service.exception

import java.lang.Exception

class NotAllowedException(override val message:String) : Exception(message)