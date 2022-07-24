package dev.vrba.dubsbot.discord

import com.vdurmont.emoji.EmojiParser
import dev.vrba.dubsbot.domain.BasicMatch
import dev.vrba.dubsbot.domain.DubsMatch
import dev.vrba.dubsbot.entity.GuildStats
import dev.vrba.dubsbot.entity.UserScore
import dev.vrba.dubsbot.repository.GuildStatsRepository
import dev.vrba.dubsbot.repository.UserScoreRepository
import dev.vrba.dubsbot.service.DubsLookupService
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.emoji.Emoji
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.springframework.stereotype.Component

@Component
class MessagesEventListener(
    private val scoreRepository: UserScoreRepository,
    private val statsRepository: GuildStatsRepository,
    private val service: DubsLookupService
) : ListenerAdapter() {

    override fun onMessageReceived(event: MessageReceivedEvent) {
        if (!event.isFromGuild) return

        val guild = event.guild.idLong
        val user = event.author.idLong

        val matches = service.findMatches(event.messageIdLong)
        val stats = statsRepository.findByGuild(guild) ?: GuildStats(guild = guild)

        if (matches.isNotEmpty()) {
            listOf(":four_leaf_clover:", *matches.map { it.emoji }.toTypedArray())
                .map { EmojiParser.parseToUnicode(it) }
                .map { Emoji.fromUnicode(it) }
                .forEach {
                    event.message.addReaction(it).queue()
                }

            updateScore(user, guild, matches)
        }

        // Reply with image to epic digits
        if (matches.any { it is BasicMatch && it.digits >= 4 }) {
            event.message.replyEmbeds(
                EmbedBuilder()
                    .setTitle("Woah, that are some pog digits!")
                    .setDescription("**${event.messageIdLong}**")
                    .setImage("https://gallery.lajtkep.dev/api/files/getRandomFile.php?tag=DOUBLES&seed=" + event.messageIdLong)
                    .build()
            ).queue()
        }

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
        val score = scoreRepository.findByUserAndGuild(user, guild) ?: scoreRepository.save(
            UserScore(
                user = user,
                guild = guild,
            )
        )

        scoreRepository.save(matches.fold(score) { item, match -> match.apply(item) })
    }
}