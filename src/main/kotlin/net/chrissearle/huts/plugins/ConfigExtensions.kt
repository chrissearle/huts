package net.chrissearle.huts.plugins

import io.ktor.server.config.ApplicationConfig

operator fun ApplicationConfig.get(property: String) = this.property(property).getString()
