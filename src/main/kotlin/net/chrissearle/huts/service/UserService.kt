package net.chrissearle.huts.service

import arrow.core.Either
import arrow.core.continuations.either
import arrow.core.left
import arrow.core.right
import com.password4j.Hash
import com.password4j.Password
import io.ktor.server.auth.jwt.JWTPrincipal
import mu.KotlinLogging
import net.chrissearle.huts.ApiError
import net.chrissearle.huts.CannotUpdateWithoutId
import net.chrissearle.huts.CouldNotCreateUser
import net.chrissearle.huts.CurrentUserNotFound
import net.chrissearle.huts.InvalidPassword
import net.chrissearle.huts.PasswordMismatch
import net.chrissearle.huts.UserAlreadyExists
import net.chrissearle.huts.UserNotFound
import net.chrissearle.huts.domain.model.LocalUser
import net.chrissearle.huts.domain.web.PasswordChangeRequest
import net.chrissearle.huts.domain.web.admin.CreateUserRequest
import net.chrissearle.huts.domain.web.admin.NewPasswordRequest
import net.chrissearle.huts.domain.web.admin.RoleUpdateRequest
import net.chrissearle.huts.domain.web.admin.UpdateUserRequest
import net.chrissearle.huts.plugins.role.Role
import net.chrissearle.huts.plugins.role.username
import net.chrissearle.huts.repository.UserRepository
import org.passay.CharacterRule
import org.passay.EnglishCharacterData
import org.passay.EnglishSequenceData
import org.passay.IllegalSequenceRule
import org.passay.LengthRule
import org.passay.PasswordData
import org.passay.PasswordValidator
import org.passay.WhitespaceRule

private val logger = KotlinLogging.logger {}

class UserService(private val repository: UserRepository) {

    fun all() = repository.all()

    fun checkPassword(username: String, password: String): Boolean {
        var validPassword = false

        repository.hashForUser(username)?.let { dbPassword ->
            val check = Password.check(password, dbPassword.clean()).andUpdate().withBcrypt()

            if (check.isVerified && check.isUpdated) {
                repository.storeHash(username, check.hash.result)
            }

            validPassword = check.isVerified
        }

        return validPassword
    }

    fun claims(username: String) = repository.claimsForUser(username)

    private fun String.clean() = this.replace(oldPrefix, "")

    private fun fetchUser(id: Long) = repository.userById(id)?.right() ?: UserNotFound.left()

    private fun fetchUser(username: String) = repository.userByUsername(username)?.right() ?: UserNotFound.left()

    private fun findUser(idParam: String?): Either<ApiError, LocalUser> {
        val id = idParam?.toLongOrNull() ?: return CannotUpdateWithoutId.left()

        return fetchUser(id)
    }

    private fun validatedPasswordHash(password: String): Either<ApiError, Hash> {
        val result = validator.validate(PasswordData(password))

        return when {
            result.isValid -> {
                Password.hash(password).withBcrypt().right()
            }

            else -> {
                InvalidPassword(validator.getMessages(result)).left()
            }
        }
    }

    fun createDefaultUserIfNeeded(username: String, password: String) {
        if (all().isNotEmpty()) {
            return
        }

        logger.info { "Creating default user" }

        repository.createUser(username, "Default User", listOf(Role.USER, Role.ADMIN), "system")

        val hash = Password.hash(password).withBcrypt()

        repository.storeHash(username, hash.result)
    }

    context(JWTPrincipal)
    suspend fun updatePassword(passwordChangeRequest: PasswordChangeRequest) = either {
        val username = (username()?.right() ?: CurrentUserNotFound.left()).bind()

        val user = fetchUser(username).bind()

        val newPasswordHash = if (checkPassword(username, passwordChangeRequest.oldPassword)) {
            validatedPasswordHash(passwordChangeRequest.newPassword)
        } else {
            PasswordMismatch.left()
        }.bind()

        repository.storeHash(user.username, newPasswordHash.result)

        fetchUser(user.id).bind()
    }

    context(JWTPrincipal)
    suspend fun updateRoles(idParam: String?, roleUpdate: RoleUpdateRequest) = either {
        val username = (username()?.right() ?: CurrentUserNotFound.left()).bind()

        val user = findUser(idParam).bind()

        repository.updateRoles(user.username, roleUpdate.roles, username)

        fetchUser(user.id).bind()
    }

    context(JWTPrincipal)
    suspend fun updateUser(idParam: String?, updateUser: UpdateUserRequest) = either {
        val username = (username()?.right() ?: CurrentUserNotFound.left()).bind()

        val user = findUser(idParam).bind()

        repository.updateUser(user.id, updateUser.username, updateUser.name, username)

        fetchUser(user.id).bind()
    }

    context(JWTPrincipal)
    suspend fun createUser(user: CreateUserRequest) = either {
        val username = (username()?.right() ?: CurrentUserNotFound.left()).bind()

        (repository.userByUsername(user.username)?.let { UserAlreadyExists.left() } ?: null.right()).bind()

        (
            repository.createUser(user.username, user.name, user.roles, username)?.right()
                ?: CouldNotCreateUser.left()
            ).bind()

        fetchUser(user.username).bind()
    }

    suspend fun deleteUser(idParam: String?) = either {
        val user = findUser(idParam).bind()

        repository.deleteUser(user.id)

        user
    }

    context(JWTPrincipal)
    suspend fun changePassword(idParam: String?, newPassword: NewPasswordRequest) = either {
        val username = (username()?.right() ?: CurrentUserNotFound.left()).bind()

        val user = findUser(idParam).bind()

        val hash = validatedPasswordHash(newPassword.password).bind()

        repository.storeHash(user.username, hash.result, username)

        fetchUser(user.id).bind()
    }

    companion object {
        const val oldPrefix = "{bcrypt}"

        val validator = PasswordValidator(
            LengthRule(8, 16),
            CharacterRule(EnglishCharacterData.UpperCase, 1),
            CharacterRule(EnglishCharacterData.LowerCase, 1),
            CharacterRule(EnglishCharacterData.Digit, 1),
            IllegalSequenceRule(EnglishSequenceData.Alphabetical, 5, false),
            IllegalSequenceRule(EnglishSequenceData.Numerical, 5, false),
            IllegalSequenceRule(EnglishSequenceData.USQwerty, 5, false),
            WhitespaceRule()
        )
    }
}
