package net.chrissearle.huts.domain.web

import net.chrissearle.huts.domain.model.Hut
import java.time.LocalDate

data class CreateBookingRequest(val name: String, val hut: Hut, val fromDate: LocalDate, val toDate: LocalDate)
