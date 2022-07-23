package dev.vrba.dubsbot.discord.commands

import dev.vrba.dubsbot.domain.DubsMatch
import dev.vrba.dubsbot.service.DubsLookupService
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.MessageEmbed
import net.dv8tion.jda.api.events.interaction.command.GenericCommandInteractionEvent
import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent
import net.dv8tion.jda.api.interactions.commands.build.CommandData
import net.dv8tion.jda.api.interactions.commands.build.Commands
import net.dv8tion.jda.api.interactions.components.buttons.Button
import org.springframework.stereotype.Component

@Component
class ViewMessageIdCommand(
    private val service: DubsLookupService,
) : ApplicationCommand {

    override fun define(): CommandData = Commands.message("View message id")

    override fun handle(event: GenericCommandInteractionEvent) {
        if (event !is MessageContextInteractionEvent || !event.isFromGuild) return

        val message = event.target.idLong
        val matches = service.findMatches(message)

        val embed = if (matches.isEmpty()) noMatchesEmbed(message) else matchesEmbed(message, matches)
        val button = Button.link(event.target.jumpUrl, "Message")

        return event
            .replyEmbeds(embed)
            .addActionRow(button)
            .queue()
    }

    private fun noMatchesEmbed(message: Long): MessageEmbed {
        return EmbedBuilder()
            .setColor(0xED4245)
            .setTitle("No dubs")
            .setDescription("`$message`")
            .build()
    }

    private fun matchesEmbed(message: Long, matches: Set<DubsMatch>): MessageEmbed {
        return EmbedBuilder()
            .setColor(0x57F287)
            .setTitle("Check 'em")
            .setDescription("`$message`")
            .addField("Matches: ", matches.joinToString(", ") { it.name }, false)
            .build()
    }
}