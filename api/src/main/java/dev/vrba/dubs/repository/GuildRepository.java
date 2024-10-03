package dev.vrba.dubs.repository;

import dev.vrba.dubs.domain.Guild;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface GuildRepository extends CrudRepository<Guild, String> {
}
