package net.chrissearle.huts.repository

import kotliquery.Row
import kotliquery.Session
import kotliquery.queryOf
import net.chrissearle.huts.domain.model.Booking

class BookingRepository(private val session: Session) : QueryLoader() {

    fun all() = loadQuery("booking/all.sql")?.let { query ->
        session.run(
            queryOf(query).map {
                it.toBooking()
            }.asList
        )
    } ?: emptyList()
}

fun Row.toBooking(): Booking {
    val dates = this.string("booking_dates").dateRange()

    return Booking(
        id = this.long("booking_id"),
        name = this.string("booking_name"),
        hut = this.toHut(),
        fromDate = dates.first,
        toDate = dates.second
    )
}
