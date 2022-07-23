package dev.vrba.dubsbot.discord.commands

import dev.vrba.dubsbot.entity.GuildStats
import dev.vrba.dubsbot.repository.GuildStatsRepository
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.interaction.command.GenericCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.build.CommandData
import net.dv8tion.jda.api.interactions.commands.build.Commands
import org.springframework.stereotype.Component
import kotlin.math.max

@Component
class StatsCommand(private val repository: GuildStatsRepository) : ApplicationCommand {

    override fun define(): CommandData = Commands.slash("stats", "View dubs bot stats collected in this guild")

    override fun handle(event: GenericCommandInteractionEvent) {
        val guild = event.guild ?: throw IllegalStateException("This command cannot be called outside of guilds")
        val interaction = event.deferReply().complete()

        fun percentage(slice: Long, total: Long): Long {
            return (slice * 100) / max(total, 1L)
        }

        val stats = repository.findByGuild(guild.idLong) ?: GuildStats(guild = guild.idLong)
        val embed = EmbedBuilder()
            .setColor(0xFEE75C)
            .setTitle("Stats for the guild ${guild.name}")
            .addField("Total messages sent", stats.messages.toString(), false)
            .addField("Total digits", stats.digits.toString() + " (${percentage(stats.digits, stats.messages)}%)", false)
            .addField("Total patterns", stats.extra.toString() + " (${percentage(stats.extra, stats.messages)}%)", false)
            .build()

        interaction.editOriginalEmbeds(embed).complete()
    }

}