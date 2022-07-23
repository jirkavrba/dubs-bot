package dev.vrba.dubsbot.discord.commands

import dev.vrba.dubsbot.repository.UserScoreRepository
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.interaction.command.GenericCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.build.CommandData
import net.dv8tion.jda.api.interactions.commands.build.Commands
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class LeaderboardCommand(private val repository: UserScoreRepository) : ApplicationCommand {

    override fun define(): CommandData = Commands.slash("leaderboard", "View dubs leaderboard for today")

    override fun handle(event: GenericCommandInteractionEvent) {
        val guild = event.guild ?: throw IllegalStateException("This command cannot be used outside of guilds")
        val interaction = event.deferReply().complete()

        val scores = repository.findAllByGuild(guild.idLong).sortedByDescending { it.total() }
        val leaderboard = scores.mapIndexed { index, score -> "**${index + 1}:** " + score.description() }
        val embed = EmbedBuilder()
            .setColor(0x57F287)
            .setTitle("Leaderboard for guild ${guild.name}")
            .setDescription(leaderboard.joinToString("\n"))
            .setTimestamp(Instant.now())
            .build()

        interaction.editOriginalEmbeds(embed).queue()
    }
}