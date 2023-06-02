package net.chrissearle.huts.repository

import kotliquery.Row
import kotliquery.Session
import kotliquery.queryOf
import net.chrissearle.huts.domain.model.BookingRequest

class BookingRequestRepository(private val session: Session) : QueryLoader() {

    fun all() = loadQuery("booking_request/all.sql")?.let { query ->
        session.run(
            queryOf(query).map {
                it.toBookingRequest()
            }.asList
        )
    } ?: emptyList()
}

fun Row.toBookingRequest(): BookingRequest {
    val dates = this.string("booking_request_dates").dateRange()

    return BookingRequest(
        id = this.long("booking_request_id"),
        name = this.string("booking_request_name"),
        hut = this.toHut(),
        fromDate = dates.first,
        toDate = dates.second
    )
}
