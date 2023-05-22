package net.chrissearle.huts

import io.ktor.http.HttpStatusCode

sealed class ApiError(val statusCode: HttpStatusCode, val message: String)
object CannotUpdateWithoutId : ApiError(
    HttpStatusCode.BadRequest,
    "Cannot update user without ID"
)

object CurrentUserNotFound : ApiError(
    HttpStatusCode.Unauthorized,
    "Could not find current user"
)

data class InvalidPassword(val validations: List<String>) : ApiError(
    HttpStatusCode.BadRequest,
    "Invalid password"
)

object PasswordMismatch : ApiError(
    HttpStatusCode.Forbidden,
    "Old password did not match"
)

object UserAlreadyExists : ApiError(
    HttpStatusCode.Conflict,
    "Could not create user - existing user with same username"
)

object CouldNotCreateUser : ApiError(
    HttpStatusCode.InternalServerError,
    "Could not create user"
)

object UserNotFound : ApiError(
    HttpStatusCode.NotFound,
    "User not found"
)

object NotImplementedYet : ApiError(
    HttpStatusCode.BadRequest,
    "Not implemented yet"
)
