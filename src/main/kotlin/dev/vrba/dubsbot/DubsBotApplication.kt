package dev.vrba.dubsbot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan(basePackages = ["dev.vrba.dubsbot.configuration"])
@SpringBootApplication
class DubsBotApplication

fun main(args: Array<String>) {
	runApplication<DubsBotApplication>(*args)
}
