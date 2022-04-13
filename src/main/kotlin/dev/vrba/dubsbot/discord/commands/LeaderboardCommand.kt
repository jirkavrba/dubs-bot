package dev.vrba.dubsbot.discord.commands

import dev.vrba.dubsbot.entities.Score
import dev.vrba.dubsbot.repositories.ScoreRepository
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.interaction.command.GenericCommandInteractionEvent
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.build.Commands
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class LeaderboardCommand(private val repository: ScoreRepository) : ApplicationCommand {

    override fun define(): SlashCommandData = Commands.slash("leaderboard", "Display the dubs bot leaderboard for this guild")

    override fun handle(event: GenericCommandInteractionEvent) {
        val interaction = event.deferReply().complete()
        val guild = event.guild?.idLong
            ?: return interaction
                .editOriginal("Sorry, this command is meant to be used in guilds only.")
                .queue()

        val sort = Sort.by(Sort.Direction.DESC, "decas", "nonas", "octas", "septs", "sextas", "pents", "quads", "trips", "dubs")
        val leaderboard = repository.findByGuildId(guild, sort)
            .take(10)
            .mapIndexed(this::formatScore)

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

    private fun formatScore(index: Int, score: Score): String {
        val header = "${index + 1}. <@${score.userId}>: "
        val counts = listOf(
            "decas" to score.decas,
            "nonas" to score.nonas,
            "octas" to score.octas,
            "septs" to score.septs,
            "sextas" to score.sextas,
            "pents" to score.pents,
            "quads" to score.quads,
            "trips" to score.trips,
            "dubs" to score.dubs
        )
        .filter { (_, score) -> score > 0 }
        .joinToString(", ") { (label, score) -> "**$score** $label" }

        if (counts.isEmpty()) {
            return header + "_No dubs yet_ \uD83D\uDE22"
        }

        return header + counts
    }
}