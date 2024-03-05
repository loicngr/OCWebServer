package fr.loicnogier

import fr.loicnogier.models.Course
import fr.loicnogier.models.courseStorage
import fr.loicnogier.utils.findById
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import kotlin.test.*

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
            module()
        }

        val response = client.get("/")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("Welcome to OpenClassrooms brand new server !", response.bodyAsText())
    }

    @Test
    fun testJsonPostValidation() = testApplication {
        application {
            module()
        }

        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }

        val response = client.post("/course") {
            contentType(ContentType.Application.Json)
            setBody(Course.createBlank())
        }

        assertEquals(HttpStatusCode.BadRequest, response.status)
        assertEquals("A course title is required", response.bodyAsText())
    }

    @Test
    fun testJsonPutValidation() = testApplication {
        application {
            module()
        }

        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }

        val course1 = findById(courseStorage, 1)
        assertNotNull(course1)

        val response = client.put("/course/1") {
            contentType(ContentType.Application.Json)
            setBody(course1.apply {
                title = "Modified title"
            })
        }

        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(course1, response.body())
    }
}