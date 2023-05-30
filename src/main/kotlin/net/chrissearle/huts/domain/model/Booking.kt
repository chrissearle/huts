package net.chrissearle.huts.domain.model

import java.time.LocalDate

data class Booking(val id: Long, val name: String, val hut: Hut, val fromDate: LocalDate, val toDate: LocalDate)
