package fr.loicnogier.plugins

import fr.loicnogier.models.Course
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*

fun Application.configureRequestValidation() {
    install(RequestValidation) {
        validate<Course> { course ->
            if (course.title == null) {
                ValidationResult.Invalid("A course title is required")
            } else ValidationResult.Valid
        }
    }
}
