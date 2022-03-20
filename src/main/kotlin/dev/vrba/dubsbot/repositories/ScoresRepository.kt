package dev.vrba.dubsbot.repositories

import dev.vrba.dubsbot.entities.Score
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ScoresRepository : CrudRepository<Score, UUID>