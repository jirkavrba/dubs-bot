package dev.vrba.dubsbot.discord

import dev.vrba.dubsbot.entities.Score
import dev.vrba.dubsbot.repositories.ScoreRepository
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.springframework.stereotype.Component

@Component
class MessageEventListener(private val repository: ScoreRepository) : ListenerAdapter() {

    override fun onMessageReceived(event: MessageReceivedEvent) {
        val number = checkEm(event.messageId)
        val emoji = when (number) {
            2 -> "2️⃣"
            3 -> "3️⃣"
            4 -> "4️⃣"
            5 -> "5️⃣"
            6 -> "6️⃣"
            7 -> "7️⃣"
            8 -> "8️⃣"
            9 -> "9️⃣"
            10 -> "\uD83D\uDD1F" // I fucking hate unicode <3
            else -> null
        }

        val guild = event.guild.idLong
        val user = event.author.idLong

        emoji?.let {
            event.message.addReaction("\uD83C\uDF40").queue()
            event.message.addReaction(it).queue()

            updateScore(guild, user, number)
        }
    }

    // Check 'em, dubs
    private fun checkEm(id: String): Int {
        val reversed = id.reversed()
        val first = reversed.first()

        return reversed.takeWhile { it == first }.count()
    }

    private fun updateScore(guild: Long, user: Long, number: Int) {
        val score = repository.findByUserIdAndGuildId(user, guild) ?: Score(userId = user, guildId = guild)
        val updated = when (number) {
            2 -> score.copy(dubs = score.dubs + 1)
            3 -> score.copy(trips = score.trips + 1)
            4 -> score.copy(quads = score.quads + 1)
            5 -> score.copy(pents = score.pents + 1)
            6 -> score.copy(sextas = score.sextas + 1)
            7 -> score.copy(septs = score.septs + 1)
            8 -> score.copy(octas = score.octas + 1)
            9 -> score.copy(nonas = score.nonas + 1)
            10 -> score.copy(decas = score.decas + 1)
            else -> score
        }

        repository.save(updated)
    }
}