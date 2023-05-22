package net.chrissearle.huts.domain.model

import net.chrissearle.huts.plugins.role.Role

data class UserClaims(
    val username: String,
    val roles: List<Role>,
    val name: String,
    val number: String?
)
