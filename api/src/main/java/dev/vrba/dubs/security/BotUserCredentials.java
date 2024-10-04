package dev.vrba.dubs.security;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.core.annotation.NonNull;

@ConfigurationProperties("credentials.bot-user")
public class BotUserCredentials {
    @NonNull
    String username;

    @NonNull
    String password;
}
