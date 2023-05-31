package net.chrissearle.huts.plugins

import arrow.core.Either
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import net.chrissearle.huts.ApiError
import net.chrissearle.huts.InvalidPassword

data class Response<T>(
    val code: HttpStatusCode,
    val message: T
)

suspend fun ApplicationCall.apiRespond(block: suspend () -> Either<ApiError, Any>) {
    val response = block().respond()

    this.respond(response.code, response.message)
}

fun <T : Any> Either<ApiError, T>.respond(): Response<Any> = this.fold({
    when (it) {
        is InvalidPassword -> it.buildResponse(InvalidPasswordResponse(validations = it.validations))
        else -> it.buildResponse(ApiErrorResponse(message = it.message))
    }
}, {
    Response(HttpStatusCode.OK, it)
})

data class InvalidPasswordResponse(
    val validations: List<String>
)
data class ApiErrorResponse(
    val message: String
)

private fun ApiError.buildResponse(body: Any): Response<Any> {
    return Response(this.statusCode, body)
}
