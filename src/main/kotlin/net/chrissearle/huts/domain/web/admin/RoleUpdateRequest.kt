package net.chrissearle.huts.domain.web.admin

import net.chrissearle.huts.plugins.role.Role

data class RoleUpdateRequest(
    val roles: List<Role>
)
