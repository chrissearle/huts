package net.chrissearle.huts.plugins

import io.ktor.server.application.Application
import io.ktor.server.auth.authenticate
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import net.chrissearle.huts.plugins.role.Role
import net.chrissearle.huts.plugins.role.withRole
import net.chrissearle.huts.service.UserService

fun Application.configureUserRouting(service: UserService) {
    routing {
        route("/api") {
            authenticate("auth-jwt") {
                withRole(Role.USER) {
                }
                withRole(Role.ADMIN) {
                    route("/admin") {
                    }
                }
            }
        }
    }
}
