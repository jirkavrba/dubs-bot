package dev.vrba.dubs.repository;

import dev.vrba.dubs.domain.Channel;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface ChannelRepository extends CrudRepository<Channel, String> {
}
