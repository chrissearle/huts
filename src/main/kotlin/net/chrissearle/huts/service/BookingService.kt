package net.chrissearle.huts.service

import net.chrissearle.huts.repository.BookingRepository

class BookingService(private val repository: BookingRepository) {

    fun all() = repository.all()
}
