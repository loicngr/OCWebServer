package fr.loicnogier.models

import fr.loicnogier.interfaces.ItemInterface
import kotlinx.serialization.*

const val COURSE_LEVEL_LOW: Int = 1
const val COURSE_LEVEL_MID: Int = 2
const val COURSE_LEVEL_HIGH: Int = 3

@Serializable
data class Course(
    override var id: Int? = -1,
    var title: String? = null,
    var level: Int = COURSE_LEVEL_LOW,
    var top: Boolean = false,
    var enabled: Boolean = false,
) : ItemInterface {
    companion object {
        fun createBlank() = Course(-1, enabled = true)
    }
}

var courseLastInsertId: Int = 3

val courseStorage = mutableListOf<Course>(
    Course(
        id = 1,
        title = "First course",
        level = COURSE_LEVEL_LOW,
        enabled = true,
    ),
    Course(
        id = 2,
        title = "Second course",
        level = COURSE_LEVEL_MID,
        enabled = true,
        top = true,
    ),
    Course(
        id = courseLastInsertId,
        title = "Final course",
        level = COURSE_LEVEL_HIGH,
        enabled = true,
    ),
)
