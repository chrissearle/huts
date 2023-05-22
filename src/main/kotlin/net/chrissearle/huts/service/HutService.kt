package net.chrissearle.huts.service

import net.chrissearle.huts.repository.HutRepository

class HutService(private val repository: HutRepository) {

    fun all() = repository.all()
}
