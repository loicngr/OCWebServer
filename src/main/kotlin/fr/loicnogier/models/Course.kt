package fr.loicnogier.models

import kotlinx.serialization.*

const val COURSE_LEVEL_LOW: Int = 1
const val COURSE_LEVEL_MID: Int = 2
const val COURSE_LEVEL_HIGH: Int = 3

@Serializable
data class Course(var id: String, var title: String = "", var level: Int = COURSE_LEVEL_LOW, var enabled: Boolean = false)

val courseStorage = mutableListOf<Course>(
    Course(
        id = "1",
        title = "First course",
        level = COURSE_LEVEL_LOW,
        enabled = true
    ),
    Course(
        id = "2",
        title = "Second course",
        level = COURSE_LEVEL_MID,
        enabled = true
    ),
    Course(
        id = "3",
        title = "Final course",
        level = COURSE_LEVEL_HIGH,
        enabled = true
    ),
)
