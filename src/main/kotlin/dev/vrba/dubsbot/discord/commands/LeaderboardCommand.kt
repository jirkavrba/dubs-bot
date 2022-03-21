package dev.vrba.dubsbot.discord.commands

import dev.vrba.dubsbot.repositories.ScoreRepository
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.build.Commands
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class LeaderboardCommand(private val repository: ScoreRepository) : SlashCommand {

    override fun define(): SlashCommandData = Commands.slash("leaderboard", "Display the dubs bot leaderboard for this guild")

    override fun handle(event: SlashCommandInteractionEvent) {
        val interaction = event.deferReply().complete()
        val guild = event.guild?.idLong
            ?: return interaction
                .editOriginal("Sorry, this command is meant to be used in guilds only.")
                .queue()

        val sort = Sort.by(Sort.Direction.DESC, "pents", "quads", "trips", "dubs")
        val leaderboard = repository.findByGuildId(guild, sort)
            .take(10)
            .mapIndexed { i, it ->
                "**${i + 1}**. <@${it.userId}> - **${it.pents}** pents, **${it.quads}** quads, **${it.trips}** trips and **${it.dubs}** dubs"
            }

        val embed = EmbedBuilder()
            .setColor(0x57F287)
            .setTitle("Dubs bot leaderboard for this guild")
            .setDescription(leaderboard.joinToString("\n"))
            .setTimestamp(Instant.now())
            .build()

        interaction.editOriginalEmbeds(embed)
            .setContent("")
            .queue()
    }
}