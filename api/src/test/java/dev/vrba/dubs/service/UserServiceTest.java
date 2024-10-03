package dev.vrba.dubs.service;

import dev.vrba.dubs.domain.User;
import dev.vrba.dubs.repository.UserRepository;
import io.micronaut.context.annotation.Property;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
@Property(name = "datasources.default.url", value = "jdbc:tc:postgresql:latest:///dubs")
@Property(name = "datasources.default.driver-class-name", value = "org.testcontainers.jdbc.ContainerDatabaseDriver")
@Testcontainers(disabledWithoutDocker = true)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest {

    @Inject
    private UserRepository repository;

    @Inject
    private UserService service;

    @BeforeEach
    void setup() {
        repository.deleteAll();
        repository.save(
                new User(
                        "1",
                        "test",
                        "https://avatar.com",
                        BigInteger.ZERO
                )
        );
    }

    @Test
    @DisplayName("Should save new users")
    void shouldSaveNewUsers() {
        final User result = service.upsertUser("2", "test 2", "https://another.avatar");

        assertEquals("2", result.getId());
        assertEquals("test 2", result.getName());
        assertEquals("https://another.avatar", result.getAvatar());

        assertEquals(2, repository.count());
    }

    @Test
    @DisplayName("Should update existing users")
    void shouldUpdateExistingUsers() {
        final User result = service.upsertUser("1", "updated test", "https://updated.avatar");

        assertEquals("1", result.getId());
        assertEquals("updated test", result.getName());
        assertEquals("https://updated.avatar", result.getAvatar());

        assertEquals(1, repository.count());
    }
}
