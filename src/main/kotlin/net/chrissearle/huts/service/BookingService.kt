package net.chrissearle.huts.service

import net.chrissearle.huts.repository.BookingRepository
import net.chrissearle.huts.repository.BookingRequestRepository

class BookingService(private val repository: BookingRepository, private val requestRepository: BookingRequestRepository) {
    fun all() = repository.all()

    fun allRequests() = requestRepository.all()
}
