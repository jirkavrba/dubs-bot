package dev.vrba.dubsbot.discord.commands

import net.dv8tion.jda.api.events.interaction.command.GenericCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.build.CommandData

interface ApplicationCommand {

    fun define(): CommandData

    fun handle(event: GenericCommandInteractionEvent)

}