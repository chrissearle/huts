package net.chrissearle.huts.domain.model

import net.chrissearle.huts.plugins.role.Role

data class LocalUser(
    val id: Long,
    val username: String,
    val roles: List<Role>,
    val name: String?
)
