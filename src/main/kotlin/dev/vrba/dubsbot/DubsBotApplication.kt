package dev.vrba.dubsbot

import dev.vrba.dubsbot.configuration.DiscordConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
@EnableConfigurationProperties(DiscordConfiguration::class)
class DubsBotApplication

fun main(args: Array<String>) {
    runApplication<DubsBotApplication>(*args)
}
