package net.chrissearle.huts.repository

import kotliquery.Row
import kotliquery.Session
import kotliquery.queryOf
import net.chrissearle.huts.domain.model.BookingRequest
import net.chrissearle.huts.domain.web.CreateBookingRequest

class BookingRequestRepository(private val session: Session) : QueryLoader() {

    fun all() = loadQuery("booking_request/all.sql")?.let { query ->
        session.run(
            queryOf(query).map {
                it.toBookingRequest()
            }.asList
        )
    } ?: emptyList()

    fun bookingRequestById(id: Long) = loadQuery("booking_request/by_id.sql")?.let { query ->
        session.run(
            queryOf(
                statement = query,
                paramMap = mapOf("id" to id)
            ).map { it.toBookingRequest() }.asSingle
        )
    }

    fun createBookingRequest(data: CreateBookingRequest) = loadQuery("booking_request/create.sql")?.let { query ->
        session.run(
            queryOf(
                statement = query,
                paramMap = mapOf(
                    "name" to data.name,
                    "hut" to data.hut.id,
                    "dates" to Pair(data.fromDate, data.toDate).dateRange()
                )
            ).asUpdateAndReturnGeneratedKey
        )
    }
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
