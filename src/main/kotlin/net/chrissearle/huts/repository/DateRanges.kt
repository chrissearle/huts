package net.chrissearle.huts.repository

import java.time.LocalDate

// Format: [yyyy-mm-dd,yyyy-mm-dd)
// Square bracket - inclusive
// Parentheses - exclusive

fun String.dateRange() =
    this.drop(1).dropLast(1).split(",").map { LocalDate.parse(it) }.zipWithNext().first()

fun Pair<LocalDate, LocalDate>.dateRange() = "[${this.first},${this.second})"
