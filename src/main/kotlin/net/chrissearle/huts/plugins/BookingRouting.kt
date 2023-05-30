package net.chrissearle.huts.plugins

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.auth.authenticate
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import net.chrissearle.huts.plugins.role.Role
import net.chrissearle.huts.plugins.role.withRole
import net.chrissearle.huts.service.BookingService

fun Application.configureBookingRouting(service: BookingService) {
    routing {
        route("/api") {
            route("/open") {
                route("/booking") {
                    get {
                        call.respond(HttpStatusCode.OK, service.all())
                    }
                }
            }
            route("/hut") {
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
}
