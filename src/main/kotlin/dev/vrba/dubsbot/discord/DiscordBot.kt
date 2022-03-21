package dev.vrba.dubsbot.discord

import dev.vrba.dubsbot.configuration.DiscordConfiguration
import dev.vrba.dubsbot.discord.commands.ApplicationCommand
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.events.interaction.command.GenericCommandInteractionEvent
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.EventListener
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.springframework.boot.CommandLineRunner
import org.springframework.core.env.Environment
import org.springframework.core.env.Profiles
import org.springframework.stereotype.Component

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
        registerEventListeners()
        registerSlashCommands()
        updateBotStatus()
    }

    private fun registerEventListeners() {
        listeners.forEach {
            client.addEventListener(it)
        }
    }

    private fun registerSlashCommands() {
        val handler = object : ListenerAdapter() {
            override fun onGenericCommandInteraction(event: GenericCommandInteractionEvent) {
                // TODO: Add exception handling
                commands.firstOrNull { it.define().name == event.name }?.handle(event)
            }
        }

        client.addEventListener(handler)

        val commands = commands.map { it.define() }

        if (environment.acceptsProfiles(Profiles.of("development"))) {
            val guild = client.getGuildById(configuration.developmentGuildId) ?: throw IllegalStateException("Cannot find the configured guild")

            return guild.updateCommands()
                .addCommands(commands)
                .queue()
        }

        return client.updateCommands()
            .addCommands(commands)
            .queue()
    }

    private fun updateBotStatus() {
        client.presence.activity = Activity.watching("for dubs")
    }
}