package net.chrissearle.huts.domain.web.admin

import net.chrissearle.huts.plugins.role.Role

data class CreateUserRequest(
    val username: String,
    val roles: List<Role>,
    val name: String,
)
