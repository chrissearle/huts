package net.chrissearle.huts.plugins

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.auth.authenticate
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.patch
import io.ktor.server.routing.post
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
                    route("/user") {
                        post("/change_password") {
                            with(call.user()) {
                                call.apiRespond {
                                    service.updatePassword(call.receive())
                                }
                            }
                        }
                    }
                }
                withRole(Role.ADMIN) {
                    route("/admin") {
                        route("/user") {
                            get {
                                call.respond(HttpStatusCode.OK, service.all())
                            }
                            post {
                                with(call.user()) {
                                    call.apiRespond {
                                        service.createUser(call.receive())
                                    }
                                }
                            }
                            route("/{id}") {
                                post("/update_roles") {
                                    with(call.user()) {
                                        call.apiRespond {
                                            service.updateRoles(call.parameters["id"], call.receive())
                                        }
                                    }
                                }
                                post("/change_password") {
                                    with(call.user()) {
                                        call.apiRespond {
                                            service.changePassword(call.parameters["id"], call.receive())
                                        }
                                    }
                                }
                                patch {
                                    with(call.user()) {
                                        call.apiRespond {
                                            service.updateUser(call.parameters["id"], call.receive())
                                        }
                                    }
                                }
                                delete {
                                    call.apiRespond {
                                        service.deleteUser(call.parameters["id"])
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
