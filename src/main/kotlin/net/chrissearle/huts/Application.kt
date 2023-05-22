package net.chrissearle.huts

import io.ktor.server.application.Application
import io.ktor.server.netty.EngineMain.main
import net.chrissearle.huts.plugins.configureMonitoring
import net.chrissearle.huts.plugins.configureRouting
import net.chrissearle.huts.plugins.configureSecurity
import net.chrissearle.huts.plugins.configureSerialization

fun main(args: Array<String>): Unit = main(args)

fun Application.module() {
    configureSerialization()
    configureMonitoring()
    configureSecurity()
    configureRouting()
}
