package dev.vrba.dubsbot.repository

import dev.vrba.dubsbot.entity.UserScore
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface UserScoreRepository : CrudRepository<UserScore, Int> {

    fun findByUserAndGuild(user: Long, guild: Long): UserScore?

    fun findAllByGuild(guild: Long): List<UserScore>

}