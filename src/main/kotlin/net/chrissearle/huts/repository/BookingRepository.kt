package net.chrissearle.huts.repository

import kotliquery.Row
import kotliquery.Session
import kotliquery.queryOf
import net.chrissearle.huts.domain.model.Booking
import java.time.LocalDate

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
    val dates = this.string("booking_dates").dateRangeToDates()

    return Booking(
        id = this.long("booking_id"),
        name = this.string("booking_name"),
        hut = this.toHut(),
        fromDate = dates.first,
        toDate = dates.second
    )
}

// Format is [yyyy-mm-dd,yyyy-mm-dd)
private fun String.dateRangeToDates() =
    this.drop(1).dropLast(1).split(",").map { LocalDate.parse(it) }.zipWithNext().first()
