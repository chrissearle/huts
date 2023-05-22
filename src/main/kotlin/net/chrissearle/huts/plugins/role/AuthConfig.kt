package net.chrissearle.huts.plugins.role

enum class Role {
    USER, ADMIN
}

class AuthConfig {
    lateinit var role: Role
}
