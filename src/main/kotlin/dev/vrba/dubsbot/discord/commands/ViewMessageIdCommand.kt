package dev.vrba.dubsbot.discord.commands

import dev.vrba.dubsbot.dubsNumber
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.interaction.command.GenericCommandInteractionEvent
import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent
import net.dv8tion.jda.api.interactions.commands.build.CommandData
import net.dv8tion.jda.api.interactions.commands.build.Commands
import net.dv8tion.jda.api.interactions.components.buttons.Button
import org.springframework.stereotype.Component

@Component
class ViewMessageIdCommand : ApplicationCommand {

    override fun define(): CommandData = Commands.message("View message id")

    override fun handle(event: GenericCommandInteractionEvent) {
        if (event !is MessageContextInteractionEvent) return

        val id = event.target.id
        val number = id.dubsNumber()
        val containsDubs = number > 1

        val button = Button.link(event.target.jumpUrl, "Message")

        if (containsDubs) {
            val split = id.length - number
            val description = id.substring(0..split) + "**" + id.substring(split) + "**"
            val embed = EmbedBuilder()
                .setColor(0x57F287)
                .setTitle("Check 'em!")
                .setDescription(description)
                .build()

            return event.replyEmbeds(embed).addActionRow(button).queue()
        }

        val embed = EmbedBuilder()
            .setColor(0xED4245)
            .setTitle("No dubs")
            .setDescription(id)
            .build()

        return event.replyEmbeds(embed).addActionRow(button).queue()
    }
}