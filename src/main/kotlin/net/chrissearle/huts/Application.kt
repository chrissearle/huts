package net.chrissearle.huts

import io.ktor.server.application.Application
import io.ktor.server.netty.EngineMain.main
import net.chrissearle.huts.plugins.configureMonitoring
import net.chrissearle.huts.plugins.configureRouting
import net.chrissearle.huts.plugins.configureSecurity
import net.chrissearle.huts.plugins.configureSerialization
import net.chrissearle.huts.plugins.configureServices
import net.chrissearle.huts.plugins.dataSource

fun main(args: Array<String>): Unit = main(args)

fun Application.module() {
    val dataSource = dataSource()

    configureSerialization()
    configureMonitoring()
    configureSecurity()
    configureRouting()
    configureServices(dataSource)
}
