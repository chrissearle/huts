package net.chrissearle.huts.plugins.role

import io.ktor.server.application.install
import io.ktor.server.routing.Route
import io.ktor.server.routing.RouteSelector
import io.ktor.server.routing.RouteSelectorEvaluation
import io.ktor.server.routing.RoutingResolveContext

class AuthorizedRouteSelector(private val desc: String) : RouteSelector() {
    override fun evaluate(context: RoutingResolveContext, segmentIndex: Int): RouteSelectorEvaluation =
        RouteSelectorEvaluation.Constant

    override fun toString(): String = "Authorize: $desc"
}

fun Route.withRole(role: Role, build: Route.() -> Unit) =
    authorizedRoute(requiredRole = role, build = build)

private fun Route.authorizedRoute(
    requiredRole: Role,
    build: Route.() -> Unit
): Route {
    val authorizedRoute = createChild(AuthorizedRouteSelector(requiredRole.toString()))

    authorizedRoute.install(RoleBasedAuthentication) {
        role = requiredRole
    }

    authorizedRoute.build()

    return authorizedRoute
}
