package dev.vrba.dubsbot.discord

import com.vdurmont.emoji.EmojiParser
import dev.vrba.dubsbot.domain.BasicMatch
import dev.vrba.dubsbot.domain.DubsMatch
import dev.vrba.dubsbot.entity.GuildSettings
import dev.vrba.dubsbot.entity.GuildStats
import dev.vrba.dubsbot.entity.UserScore
import dev.vrba.dubsbot.repository.GuildSettingsRepository
import dev.vrba.dubsbot.repository.GuildStatsRepository
import dev.vrba.dubsbot.repository.UserScoreRepository
import dev.vrba.dubsbot.service.DubsLookupService
import net.dv8tion.jda.api.entities.emoji.Emoji
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class MessagesEventListener(
    private val scoreRepository: UserScoreRepository,
    private val settingsRepository: GuildSettingsRepository,
    private val statsRepository: GuildStatsRepository,
    private val service: DubsLookupService
) : ListenerAdapter() {

    override fun onMessageReceived(event: MessageReceivedEvent) {
        if (!event.isFromGuild) return

        val guild = event.guild.idLong
        val user = event.author.idLong

        val settings = settingsRepository.findByGuild(guild) ?: settingsRepository.save(GuildSettings(guild = guild))
        val matches = service.findMatches(event.messageIdLong, settings.mode)
        val stats = statsRepository.findByGuild(guild) ?: GuildStats(guild = guild)

        if (matches.isNotEmpty()) {
            val emoji = listOf(":four_leaf_clover:") + matches.map { it.emoji }
            val unicode = emoji.map { EmojiParser.parseToUnicode(it) }

            unicode.forEach { event.message.addReaction(Emoji.fromUnicode(it)).queue() }
            updateScore(user, guild, matches)
        }

        println(settings.mode)

        // Update guild stats
        statsRepository.save(
            stats.copy(
                messages = stats.messages + 1,
                digits = stats.digits + if (matches.any { it is BasicMatch }) 1 else 0,
                extra = stats.extra + if (matches.any { it !is BasicMatch }) 1 else 0
            )
        )
    }

    private fun updateScore(user: Long, guild: Long, matches: Set<DubsMatch>) {
        val score = scoreRepository.findByUserAndGuildAndDate(user, guild, LocalDate.now()) ?: scoreRepository.save(
            UserScore(
                user = user,
                guild = guild,
                date = LocalDate.now()
            )
        )

        scoreRepository.save(matches.fold(score) { item, match -> match.apply(item) })
    }
}