package fr.loicnogier.routes

import fr.loicnogier.models.Course
import fr.loicnogier.models.courseStorage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.courseRouting() {
    route("/course") {
        get {
            call.respond(courseStorage)
        }
        get("top") {
            call.respond(courseStorage.first())
        }
        get("{id?}") {
            val id: String = call.parameters["id"] ?: return@get call.respondText(
                text = "No identifier",
                status = HttpStatusCode.BadRequest
            )

            val course: Course = courseStorage.find { c: Course -> c.id == id } ?: return@get call.respondText(
                text = "Course not found",
                status = HttpStatusCode.NotFound
            )

            call.respond(course)
        }
    }
}