package dev.vrba.dubsbot.discord.commands

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.build.Commands
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData
import net.dv8tion.jda.api.interactions.components.buttons.Button
import org.springframework.stereotype.Component

@Component
class AboutCommand : SlashCommand {

    override fun define(): SlashCommandData = Commands.slash("about", "Provides information about the bot")

    override fun handle(event: SlashCommandInteractionEvent) {
        val embed = EmbedBuilder()
            .setTitle("Dubs bot", "https://github.com/jirkavrba/dubs-bot")
            .setDescription(
                """
                **A bot for detecting accidental dubs/trips/quads among your Discord messages**

                Once a dub/trip/quad/five is detected, the bot will add a four-leaf-clover emoji and a number emoji reaction to the message
                and update your score within the current guild leaderboard.
                """.trimIndent()
            )
            .addField(
                "Okay. What the f*ck is a dub?",
                """
                Dubs are messages whose ID ends with two matching numbers. The same goes for trips (3 matching numbers), quads, fives, etc.

                For example, a message with an ID of _954324125151797300_ is a dub because it ends with two zeros.
                """.trimIndent(),
                false
            )
            .addField(
                "Okay, but is it any useful?",
                """
                **Short answer**:
                No.

                **Long answer**:
                You can compete with your online Discord friends to get the highest amount of dubs, trips, quads, etc.
                If you allow this bot to create slash commands in your guild when adding it, you can use the `/leaderboard` command to view the leaders, based on the number of dups.
                """.trimIndent(),
                false
            )
            .build()


        event.replyEmbeds(embed)
            .addActionRow(Button.link("https://github.com/jirkavrba/dubs-bot", "Github repository"))
            .queue()
    }
}