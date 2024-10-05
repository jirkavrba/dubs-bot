package dev.vrba.dubs.repository;

import dev.vrba.dubs.domain.Score;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface ScoreRepository extends CrudRepository<Score, Integer> {
}
