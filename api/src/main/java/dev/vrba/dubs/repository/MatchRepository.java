package dev.vrba.dubs.repository;

import dev.vrba.dubs.domain.Match;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface MatchRepository extends CrudRepository<Match, Integer> {
}
