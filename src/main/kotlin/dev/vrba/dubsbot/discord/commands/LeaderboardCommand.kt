package dev.vrba.dubsbot.discord.commands

import dev.vrba.dubsbot.repositories.ScoresRepository
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.build.Commands
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class LeaderboardCommand(private val repository: ScoresRepository) : SlashCommand {

    override fun define(): SlashCommandData = Commands.slash("leaderboard", "Display the dubs bot leaderboard for this guild")

    override fun handle(event: SlashCommandInteractionEvent) {
        val interaction = event.deferReply().complete()
        val guild = event.guild?.idLong
            ?: return interaction
                .editOriginal("Sorry, this command is meant to be used in guilds only.")
                .queue()

        val leaderboard = repository.guildLeaderboard(guild)
            .take(10)
            .mapIndexed { i, it ->
                "**${i + 1}**. <@${it.userId}> - **${it.pents}** pents, **${it.quads}** quads, **${it.trips}** trips and **${it.quads}** dubs"
            }

        val embed = EmbedBuilder()
            .setColor(0x99ff99)
            .setTitle("Dubs bot leaderboard for this guild")
            .setDescription(leaderboard.joinToString("\n"))
            .setTimestamp(Instant.now())
            .build()


        interaction.editOriginalEmbeds(embed)
            .setContent("")
            .queue()
    }
}