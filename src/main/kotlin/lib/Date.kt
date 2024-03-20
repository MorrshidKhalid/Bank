package org.example.lib

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class Date {

    companion object {

        // Get time and date from the system.
        fun getCurrentDateAndTime(): String = "${LocalDate.now()} - ${getCurrentTime()}"

        private fun getCurrentTime(): String {
            val formatter = DateTimeFormatter.ofPattern("HH:mm:ss a")
            val current = LocalTime.now()
            return current.format(formatter)
        }
    }
}