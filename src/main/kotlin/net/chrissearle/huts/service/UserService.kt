package net.chrissearle.huts.service

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.password4j.Hash
import com.password4j.Password
import mu.KotlinLogging
import net.chrissearle.huts.ApiError
import net.chrissearle.huts.CannotUpdateWithoutId
import net.chrissearle.huts.InvalidPassword
import net.chrissearle.huts.UserNotFound
import net.chrissearle.huts.domain.model.LocalUser
import net.chrissearle.huts.plugins.role.Role
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
