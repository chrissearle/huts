package net.chrissearle.huts

import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.server.testing.testApplication
import org.junit.jupiter.api.Test

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        client.get("/api/metrics").apply {
            status shouldBe HttpStatusCode.OK
            contentType().toString() shouldBe "text/plain; charset=UTF-8"

            val metrics = bodyAsText()

            metrics shouldContain "HELP"
            metrics shouldContain "TYPE"
            metrics shouldContain "nonheap"
        }
    }
}
