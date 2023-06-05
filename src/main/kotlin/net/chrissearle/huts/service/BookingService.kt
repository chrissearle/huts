package net.chrissearle.huts.service

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import net.chrissearle.huts.ApiError
import net.chrissearle.huts.BookingRequestNotFound
import net.chrissearle.huts.CouldNotCreateBookingRequest
import net.chrissearle.huts.domain.model.BookingRequest
import net.chrissearle.huts.domain.web.CreateBookingRequest
import net.chrissearle.huts.repository.BookingRepository
import net.chrissearle.huts.repository.BookingRequestRepository

class BookingService(
    private val repository: BookingRepository,
    private val requestRepository: BookingRequestRepository
) {
    fun all() = repository.all()

    fun allRequests() = requestRepository.all()

    fun createBookingRequest(bookingRequest: CreateBookingRequest): Either<ApiError, BookingRequest> =
        requestRepository.createBookingRequest(bookingRequest).let { id ->
            when (id) {
                null -> CouldNotCreateBookingRequest.left()
                else -> requestRepository.bookingRequestById(id)?.right() ?: BookingRequestNotFound.left()
            }
        }
}
