package dev.vrba.dubsbot.discord

import dev.vrba.dubsbot.configuration.DiscordConfiguration
import dev.vrba.dubsbot.discord.commands.ApplicationCommand
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.events.interaction.command.GenericCommandInteractionEvent
import net.dv8tion.jda.api.hooks.EventListener
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import org.springframework.boot.CommandLineRunner
import org.springframework.core.env.Profiles

@Component
class DiscordBot(
    private val configuration: DiscordConfiguration,
    private val environment: Environment,
    private val listeners: List<EventListener>,
    private val commands: List<ApplicationCommand>
) : CommandLineRunner {
    private val client: JDA = JDABuilder.createDefault(configuration.token)
        .build()
        .awaitReady()

    override fun run(vararg args: String) {
        registerApplicationCommands()
        registerEventListeners()
        updateBotStatus()
    }

    private fun registerApplicationCommands() {
        val definitions = commands.map { it.define() }
        val handler = object : ListenerAdapter() {
            override fun onGenericCommandInteraction(event: GenericCommandInteractionEvent) {
                commands.firstOrNull { it.define().name == event.name }?.handle(event)
            }
        }

        client.addEventListener(handler)

        if (environment.acceptsProfiles(Profiles.of("development"))) {
            return client.getGuildById(configuration.developmentGuildId)
                ?.updateCommands()
                ?.addCommands(definitions)
                ?.queue()
                ?: throw Exception("Development guild not found!")
        }

        return client.updateCommands()
            .addCommands(definitions)
            .queue()
    }

    private fun registerEventListeners() {
        listeners.forEach { client.addEventListener(it) }
    }

    private fun updateBotStatus() {
        client.presence.activity = Activity.watching("for dubs")
    }
}