package fr.loicnogier.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import fr.loicnogier.models.*
import fr.loicnogier.utils.*
import io.ktor.server.request.*

fun Route.courseRouting() {
    route("/course") {
        get {
            if (courseStorage.isEmpty()) {
                return@get call.respondText(
                    text = "No courses found",
                    status = HttpStatusCode.OK
                )
            }

            call.respond(courseStorage)
        }
        get("top") {
            if (courseStorage.isEmpty()) {
                return@get call.respondText(
                    text = "No courses found",
                    status = HttpStatusCode.OK
                )
            }

            val topCourse: Course = courseStorage.find { c: Course -> c.top } ?: return@get call.respondText(
                text = "No top course found",
                status = HttpStatusCode.OK
            )

            call.respond(topCourse)
        }
        get("{id?}") {
            val id: Int = call.parameters["id"]?.toIntOrNull() ?: return@get call.respondText(
                text = "No identifier",
                status = HttpStatusCode.BadRequest
            )

            val course = findById<Course>(courseStorage, id) ?: return@get call.respondText(
                text = "Course not found",
                status = HttpStatusCode.NotFound
            )

            call.respond(course)
        }
        put("{id?}") {
            val id: Int = call.parameters["id"]?.toIntOrNull() ?: return@put call.respondText(
                text = "No identifier",
                status = HttpStatusCode.BadRequest
            )

            val oldCourseIndex = findIndexById(courseStorage, id) ?: return@put call.respondText(
                text = "Course not found",
                status = HttpStatusCode.BadRequest
            )

            val updatedCourse = call.receive<Course>()

            updatedCourse.id = id
            courseStorage[oldCourseIndex] = updatedCourse

            call.respond(updatedCourse)
        }
        post {
            val course = call.receive<Course>()
            courseLastInsertId += 1
            course.id = courseLastInsertId

            courseStorage.add(course)
            call.respond(course)
        }
        delete("{id?}") {
            val id: String = call.parameters["id"] ?: return@delete call.respondText(
                text = "No identifier",
                status = HttpStatusCode.BadRequest
            )

            val index: Int = findIndexById<Course>(courseStorage, id.toInt()) ?: return@delete call.respondText(
                text = "Course not found",
                status = HttpStatusCode.NotFound
            )

            deleteByIndex<Course>(courseStorage, index)

            call.respondText(
                text = "Course deleted",
                status = HttpStatusCode.OK
            )
        }
    }
}