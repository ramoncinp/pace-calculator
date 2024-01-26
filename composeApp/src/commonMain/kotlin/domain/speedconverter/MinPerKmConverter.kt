package domain.speedconverter

import domain.HOUR_TO_MINUTE
import domain.KM_TO_MI
import domain.removeNonNumbers
import domain.timeDecimalToFormattedTime
import domain.toDecValue

// Input format for 5'34" -> "534"
// Input format for 7'20" -> "72"
// Input format for 9'00" -> "9"
// Input format for 11'20" -> "1120"

class MinPerKmConverter : SpeedUnitConverter {
    override fun toKmPerHour(data: String): String {
        val paceDecimal = data.paceToMinutesAndSeconds()
        return (HOUR_TO_MINUTE / paceDecimal).toString()
    }

    override fun toMinPerKm(data: String): String = data

    override fun toMiPerHour(data: String): String {
        val paceDecimal = data.paceToMinutesAndSeconds()
        return (HOUR_TO_MINUTE * KM_TO_MI / paceDecimal).toString()
    }

    override fun toMinPerMi(data: String): String {
        val paceDecimal = data.paceToMinutesAndSeconds()
        val newPaceDecimal = (paceDecimal / KM_TO_MI)
        return newPaceDecimal.timeDecimalToFormattedTime()
    }

    private fun String.paceToMinutesAndSeconds(): Double {
        val paceData = this.removeNonNumbers()
        return when (paceData.length) {
            1 -> paceData.toDouble()
            2 -> {
                val minute = paceData[0].toDecValue()
                val seconds = (paceData[1].toDecValue() * 10) / 60.0
                minute + seconds
            }

            3 -> {
                val minute = paceData[0].toDecValue()
                val seconds = ((paceData[1].toDecValue() * 10) + (paceData[2].toDecValue())) / 60.0
                minute + seconds
            }

            4 -> {
                val minute = (paceData[0].toDecValue() * 10) + paceData[1].toDecValue()
                val seconds = ((paceData[2].toDecValue() * 10) + (paceData[3].toDecValue())) / 60.0
                minute + seconds
            }

            else -> 0.0
        }
    }
}
