package net.chrissearle.huts.repository

import kotliquery.Row
import kotliquery.Session
import kotliquery.queryOf
import net.chrissearle.huts.domain.model.Hut

class HutRepository(private val session: Session) : QueryLoader() {

    fun all() = loadQuery("hut/all.sql")?.let { query ->
        session.run(
            queryOf(query).map {
                it.toHut()
            }.asList
        )
    } ?: emptyList()
}

fun Row.toHut() = Hut(
    id = this.long("hut_id"),
    name = this.string("hut_name"),
)
