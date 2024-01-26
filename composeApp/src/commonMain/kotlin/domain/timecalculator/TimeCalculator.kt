package domain.timecalculator

import domain.timeDecimalToFormattedTime

class TimeCalculator(private val speedKmPerHour: Double) {

    fun calculateFiveKmTime(): String {
        val time = 5 * 60.0 / speedKmPerHour
        return time.timeDecimalToFormattedTime()
    }

    fun calculateTenKmTime(): String {
        val time = 10 * 60.0 / speedKmPerHour
        return time.timeDecimalToFormattedTime()
    }

    fun calculateHalfMarathonTime(): String {
        val time = 21 * 60.0 / speedKmPerHour
        return time.timeDecimalToFormattedTime()
    }

    fun calculateMarathonTime(): String {
        val time = 42 * 60.0 / speedKmPerHour
        return time.timeDecimalToFormattedTime()
    }
}
