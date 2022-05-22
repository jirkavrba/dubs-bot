package dev.vrba.dubsbot.discord

import net.dv8tion.jda.api.events.thread.GenericThreadEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.springframework.stereotype.Component

@Component
class ThreadEventsListener : ListenerAdapter() {

    override fun onGenericThread(event: GenericThreadEvent) {
        // Automatically join all newly created threads
        if (!event.thread.isJoined) {
            event.thread.join().queue()
        }
    }
}