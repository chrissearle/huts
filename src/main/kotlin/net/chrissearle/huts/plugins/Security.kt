package net.chrissearle.huts.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.JWTVerifier
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.call
import io.ktor.server.auth.authentication
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.jwt.jwt
import io.ktor.server.auth.principal
import io.ktor.server.config.ApplicationConfig
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import io.ktor.server.sessions.get
import io.ktor.server.sessions.sessions
import io.ktor.server.sessions.set
import net.chrissearle.huts.plugins.role.AuthenticationException
import net.chrissearle.huts.plugins.role.username

fun jwtVerifier(config: ApplicationConfig): JWTVerifier = JWT
    .require(Algorithm.HMAC256(config["jwt.secret"]))
    .withAudience(config["jwt.audience"])
    .withIssuer(config["jwt.issuer"])
    .build()

fun Application.configureSecurity() {
    val config = environment.config
    val verifier = jwtVerifier(config)

    authentication {
        jwt("auth-jwt") {
            realm = config["jwt.realm"]

            verifier(verifier)

            validate { credential ->
                if (credential.payload.audience.contains(config["jwt.audience"])) {
                    JWTPrincipal(credential.payload)
                } else {
                    null
                }
            }

            challenge { _, _ ->
                call.respond(HttpStatusCode.Unauthorized, "Token is not valid or has expired")
            }
        }
    }

    routing {
        get("/session/increment") {
            val session = call.sessions.get<MySession>() ?: MySession(0)
            call.sessions.set(session.copy(count = session.count + 1))
            call.respondText("Counter is ${session.count}. Refresh to increment.")
        }
    }
}

data class MySession(
    val count: Int
)

fun ApplicationCall.username() = user().username()

fun ApplicationCall.user() = principal<JWTPrincipal>() ?: throw AuthenticationException("User Not Found in call")
