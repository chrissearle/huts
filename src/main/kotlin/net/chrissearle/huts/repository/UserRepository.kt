package net.chrissearle.huts.repository

import kotliquery.Row
import kotliquery.Session
import kotliquery.queryOf
import net.chrissearle.huts.domain.model.LocalUser
import net.chrissearle.huts.domain.model.UserClaims
import net.chrissearle.huts.plugins.role.Role

class UserRepository(private val session: Session) : QueryLoader() {

    fun all() = loadQuery("user/all.sql")?.let { query ->
        session.run(
            queryOf(query).map {
                it.toLocalUser()
            }.asList
        )
    } ?: emptyList()

    fun claimsForUser(username: String) = loadQuery("user/claims.sql")?.let { query ->
        session.run(
            queryOf(
                statement = query,
                paramMap = mapOf("username" to username)
            ).map { it.toUserClaims() }.asSingle
        )
    }

    fun createUser(username: String, name: String, roles: List<Role>, by: String) =
        loadQuery("user/create.sql")?.let { query ->
            session.run(
                queryOf(
                    statement = query,
                    paramMap = mapOf(
                        "username" to username,
                        "name" to name,
                        "roles" to roles.joinToString(",") { it.toString() },
                        "createdBy" to by
                    )
                ).asUpdate
            )
        }

    fun deleteUser(id: Long) = loadQuery("user/delete.sql")?.let { query ->
        session.run(
            queryOf(
                statement = query,
                paramMap = mapOf(
                    "id" to id
                )
            ).asUpdate
        )
    } ?: 0

    fun hashForUser(username: String) = loadQuery("user/hash.sql")?.let { query ->
        session.run(
            queryOf(
                statement = query,
                paramMap = mapOf("username" to username)
            ).map { it.string("password") }.asSingle
        )
    }

    fun storeHash(username: String, hash: String, updatedBy: String? = null) =
        loadQuery("user/store_hash.sql")?.let { query ->
            session.run(
                queryOf(
                    statement = query,
                    paramMap = mapOf(
                        "password" to hash,
                        "username" to username,
                        "updatedBy" to (updatedBy ?: username)
                    )
                ).asUpdate
            )
        }

    fun updateRoles(username: String, roles: List<Role>, updatedBy: String?) =
        loadQuery("user/update_roles.sql")?.let { query ->
            session.run(
                queryOf(
                    statement = query,
                    paramMap = mapOf(
                        "roles" to roles.joinToString(",") { it.toString() },
                        "username" to username,
                        "updatedBy" to updatedBy
                    )
                ).asUpdate
            )
        }

    fun userById(id: Long) = loadQuery("user/by_id.sql")?.let { query ->
        session.run(
            queryOf(
                statement = query,
                paramMap = mapOf("id" to id)
            ).map { it.toLocalUser() }.asSingle
        )
    }

    fun userByUsername(username: String) = loadQuery("user/by_username.sql")?.let { query ->
        session.run(
            queryOf(
                statement = query,
                paramMap = mapOf("username" to username)
            ).map { it.toLocalUser() }.asSingle
        )
    }

    fun updateUser(
        userId: Long,
        username: String,
        name: String,
        by: String
    ) = loadQuery("user/update.sql")?.let { query ->
        session.run(
            queryOf(
                statement = query,
                paramMap = mapOf(
                    "username" to username,
                    "name" to name,
                    "id" to userId,
                    "updatedBy" to by
                )
            ).asUpdate
        )
    }
}

fun Row.toLocalUser() = LocalUser(
    id = this.long("lu_id"),
    username = this.string("username"),
    name = this.string("lu_name"),
    roles = this.string("roles").roles(),
)

fun Row.toUserClaims() = UserClaims(
    username = this.string("username"),
    roles = this.string("roles").roles(),
    name = this.string("name")
)

private fun String.roles() = this.split(",").map { Role.valueOf(it) }
