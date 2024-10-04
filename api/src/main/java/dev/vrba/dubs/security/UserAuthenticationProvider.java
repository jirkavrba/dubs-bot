package dev.vrba.dubs.security;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationFailureReason;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.provider.HttpRequestAuthenticationProvider;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Singleton
@RequiredArgsConstructor
public class UserAuthenticationProvider<B> implements HttpRequestAuthenticationProvider<B> {

    @NonNull
    private final BotUserCredentials credentials;

    @Override
    public @NonNull AuthenticationResponse authenticate(
            @Nullable HttpRequest<B> requestContext,
            @NonNull AuthenticationRequest<String, String> authRequest
    ) {
        return matches(authRequest)
                ? AuthenticationResponse.success(credentials.username, List.of("ROLE_BOT"))
                : AuthenticationResponse.failure(AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH);
    }

    private boolean matches(
            @NonNull AuthenticationRequest<String, String> request
    ) {
        return request.getIdentity().equals(credentials.username) && request.getSecret().equals(credentials.password);
    }
}
