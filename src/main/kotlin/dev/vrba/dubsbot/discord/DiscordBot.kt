package dev.vrba.dubsbot.discord

import dev.vrba.dubsbot.configuration.DiscordConfiguration
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import org.springframework.boot.CommandLineRunner

@Component
class DiscordBot(
    private val configuration: DiscordConfiguration,
    private val environment: Environment
) : CommandLineRunner {
    private val client: JDA = JDABuilder.createDefault(configuration.token)
        .build()
        .awaitReady()

    override fun run(vararg args: String) {
        // register command and event listeners
        updateBotStatus()
    }

    private fun updateBotStatus() {
        client.presence.activity = Activity.watching("for dubs")
    }
}