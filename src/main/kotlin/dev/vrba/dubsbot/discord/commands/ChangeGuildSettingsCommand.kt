package dev.vrba.dubsbot.discord.commands

import dev.vrba.dubsbot.domain.DubsLookupMode
import dev.vrba.dubsbot.entity.GuildSettings
import dev.vrba.dubsbot.repository.GuildSettingsRepository
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.events.interaction.command.GenericCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.CommandData
import net.dv8tion.jda.api.interactions.commands.build.Commands
import org.springframework.stereotype.Component

@Component
class ChangeGuildSettingsCommand(private val repository: GuildSettingsRepository) : ApplicationCommand {

    override fun define(): CommandData = Commands
        .slash("guild-settings", "Change guild settings")
        .addOption(OptionType.BOOLEAN, "extended-mode", "Use the extended lookup mode?", false)

    override fun handle(event: GenericCommandInteractionEvent) {
        if (!event.isFromGuild) throw IllegalStateException("This command cannot be used outside of guilds")
        if (!event.member!!.hasPermission(Permission.MANAGE_SERVER)) throw IllegalStateException("You don't have permissions to manage this guild")

        val interaction = event.deferReply().complete()

        val guild = event.guild?.idLong ?: return
        val extended = event.getOption("extended-mode")?.asBoolean ?: false
        val settings = repository.findByGuild(guild) ?: GuildSettings(guild = guild)

        repository.save(settings.copy(mode = if (extended) DubsLookupMode.Extended else DubsLookupMode.Basic))

        val embed = EmbedBuilder()
            .setColor(0x5865F2)
            .setTitle("Guild settings updated")
            .setDescription("Changed dubs lookup mode to **${if (extended) "extended" else "basic"}**")
            .build()

        interaction.editOriginalEmbeds(embed).queue()
    }
}