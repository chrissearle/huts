package net.chrissearle.huts.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.config.ApplicationConfig
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import mu.KotlinLogging
import net.chrissearle.huts.domain.model.UserClaims
import net.chrissearle.huts.domain.web.LoginRequest
import net.chrissearle.huts.domain.web.LoginResponse
import net.chrissearle.huts.plugins.role.AuthenticationException
import net.chrissearle.huts.service.UserService
import java.util.Date

private val logger = KotlinLogging.logger {}

fun buildToken(config: ApplicationConfig, claims: UserClaims): String = JWT.create()
    .withAudience(config["jwt.audience"])
    .withIssuer(config["jwt.issuer"])
    .withClaim("username", claims.username)
    .withClaim("name", claims.name)
    .withArrayClaim("roles", claims.roles.map { it.toString() }.toTypedArray())
    .withExpiresAt(Date(System.currentTimeMillis() + 1000 * 60 * 20))
    .sign(Algorithm.HMAC256(config["jwt.secret"]))

fun Application.configureLoginRouting(service: UserService) {
    val config = environment.config

    routing {
        post("/api/login") {
            val loginRequest = call.receive<LoginRequest>()

            if (!service.checkPassword(loginRequest.username, loginRequest.password)) {
                logger.debug { "Invalid password for ${loginRequest.username}" }
                throw AuthenticationException("Invalid password")
            }

            val claims = service.claims(loginRequest.username) ?: throw AuthenticationException("Missing claims")

            call.respond(LoginResponse(token = buildToken(config, claims)))
        }
    }
}
