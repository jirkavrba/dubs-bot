package dev.vrba.dubs.repository;

import dev.vrba.dubs.domain.User;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
}
