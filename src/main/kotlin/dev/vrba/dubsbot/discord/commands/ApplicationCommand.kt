package dev.vrba.dubsbot.discord.commands

import net.dv8tion.jda.api.events.interaction.command.GenericCommandInteractionEvent
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.CommandInteraction
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData

interface ApplicationCommand {

    fun define(): SlashCommandData

    fun handle(event: GenericCommandInteractionEvent)

}