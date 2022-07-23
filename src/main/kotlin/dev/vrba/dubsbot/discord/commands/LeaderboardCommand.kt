package dev.vrba.dubsbot.discord.commands

import dev.vrba.dubsbot.repository.UserScoreRepository
import net.dv8tion.jda.api.events.interaction.command.GenericCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.build.CommandData
import net.dv8tion.jda.api.interactions.commands.build.Commands
import org.springframework.stereotype.Component

@Component
class LeaderboardCommand(private val repository: UserScoreRepository) : ApplicationCommand {

    override fun define(): CommandData = Commands.slash("leaderboard", "View dubs leaderboard for today")

    override fun handle(event: GenericCommandInteractionEvent) {
        val guild = event.guild?.idLong ?: throw IllegalStateException("This command cannot be used outside of guilds")
        val scores = repository.findAllByGuild(guild)
    }
}