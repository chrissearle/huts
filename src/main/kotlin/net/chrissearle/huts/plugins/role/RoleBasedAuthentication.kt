package net.chrissearle.huts.plugins.role

import io.ktor.server.application.createRouteScopedPlugin
import io.ktor.server.auth.AuthenticationChecked
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal

class AuthorizationException(override val message: String? = null) : Throwable()

class AuthenticationException(override val message: String? = null) : Throwable()

val RoleBasedAuthentication = createRouteScopedPlugin(
    name = "AuthorizationPlugin",
    createConfiguration = ::AuthConfig
) {
    val requiredRole = pluginConfig.role

    on(AuthenticationChecked) { call ->
        val user = call.principal<JWTPrincipal>() ?: throw AuthenticationException(message = "Unauthenticated User")

        val userRoles = user.roles()

        if (!userRoles.contains(requiredRole)) {
            throw AuthorizationException(message = "User [${user.username()}] does not have required role [$requiredRole]: user: $userRoles")
        }
    }
}
