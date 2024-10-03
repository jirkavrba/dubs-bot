package dev.vrba.dubs.repository;

import dev.vrba.dubs.domain.MatchedPattern;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface MatchedPatternRepository extends CrudRepository<MatchedPattern, Long> {
}
