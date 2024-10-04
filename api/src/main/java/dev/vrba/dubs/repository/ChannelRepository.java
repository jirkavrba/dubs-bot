package dev.vrba.dubs.repository;

import dev.vrba.dubs.domain.Channel;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface ChannelRepository extends CrudRepository<Channel, String> {
}
