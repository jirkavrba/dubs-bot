package dev.vrba.dubsbot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DubsBotApplication

fun main(args: Array<String>) {
    runApplication<DubsBotApplication>(*args)
}
