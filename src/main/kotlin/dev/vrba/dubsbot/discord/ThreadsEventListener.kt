package dev.vrba.dubsbot.discord

import net.dv8tion.jda.api.entities.ChannelType
import net.dv8tion.jda.api.entities.ThreadChannel
import net.dv8tion.jda.api.events.channel.ChannelCreateEvent
import net.dv8tion.jda.api.events.thread.ThreadRevealedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.springframework.stereotype.Component

@Component
class ThreadsEventListener : ListenerAdapter() {

    override fun onChannelCreate(event: ChannelCreateEvent) {
        val threads = listOf(
            ChannelType.GUILD_PRIVATE_THREAD,
            ChannelType.GUILD_PUBLIC_THREAD,
            ChannelType.GUILD_NEWS_THREAD
        )

        if (event.channelType in threads) {
            joinThread(event.channel as ThreadChannel)
        }
    }

    override fun onThreadRevealed(event: ThreadRevealedEvent) {
        joinThread(event.thread)
    }

    private fun joinThread(thread: ThreadChannel) {
        if (!thread.isJoined) {
            thread.join().queue()
        }
    }
}