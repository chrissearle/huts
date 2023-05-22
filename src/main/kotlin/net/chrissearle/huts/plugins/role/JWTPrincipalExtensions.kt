package net.chrissearle.huts.plugins.role

import io.ktor.server.auth.jwt.JWTPrincipal

fun JWTPrincipal?.roles() = this?.getListClaim("roles", Role::class) ?: emptyList()

fun JWTPrincipal.username() = this["username"]
