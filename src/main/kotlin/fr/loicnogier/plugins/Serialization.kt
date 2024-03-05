package fr.loicnogier.plugins

import fr.loicnogier.routes.courseRouting
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
    }
    routing {
        courseRouting()
    }
}
