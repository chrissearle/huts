package net.chrissearle.huts.plugins

import io.ktor.server.application.Application
import kotliquery.HikariCP
import kotliquery.sessionOf
import net.chrissearle.huts.repository.UserRepository
import net.chrissearle.huts.service.UserService
import org.flywaydb.core.Flyway
import javax.sql.DataSource

fun Application.dataSource(): DataSource {
    return HikariCP.default(
        environment.config["postgres.url"],
        environment.config["postgres.user"],
        environment.config["postgres.password"]
    )
}

fun Application.configureServices(dataSource: DataSource) {
    Flyway.configure().dataSource(dataSource).load().migrate()

    val dbSession = sessionOf(dataSource, returnGeneratedKey = true)

    val userRepository = UserRepository(dbSession)
    val userService = UserService(userRepository)
    configureLoginRouting(userService)
    configureUserRouting(userService)
}
