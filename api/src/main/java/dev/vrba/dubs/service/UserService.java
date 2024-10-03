package dev.vrba.dubs.service;

import dev.vrba.dubs.domain.User;
import dev.vrba.dubs.repository.UserRepository;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;
import java.util.Optional;

@Singleton
@RequiredArgsConstructor
public class UserService {

    @NonNull
    private final UserRepository repository;

    @NonNull
    public User upsertUser(
            @NonNull String id,
            @NonNull String name,
            @NonNull String avatar
    ) {
        final Optional<User> existingUser = repository.findById(id);
        return existingUser
                .map(entity -> repository.update(entity.withName(name).withAvatar(avatar)))
                .orElseGet(() -> repository.save(new User(id, name, avatar, BigInteger.ZERO)));
    }
}
