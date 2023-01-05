package com.example.Test

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class TestApplication


fun main(args: Array<String>) {
	runApplication<TestApplication>(*args)
}
