package dev.vrba.dubsbot.discord.commands

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData

interface SlashCommand {

    fun define(): SlashCommandData

    fun handle(event: SlashCommandInteractionEvent)

}