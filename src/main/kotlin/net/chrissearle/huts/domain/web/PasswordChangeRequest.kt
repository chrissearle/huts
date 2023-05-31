package net.chrissearle.huts.domain.web

data class PasswordChangeRequest(
    val oldPassword: String,
    val newPassword: String
)
