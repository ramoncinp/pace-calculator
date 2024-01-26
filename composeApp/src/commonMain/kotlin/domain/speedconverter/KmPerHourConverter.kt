package domain.speedconverter

import domain.KM_TO_MI
import domain.timeDecimalToFormattedTime
import domain.toDoubleSafe

class KmPerHourConverter : SpeedUnitConverter {
    override fun toKmPerHour(data: String): String = data

    override fun toMinPerKm(data: String): String {
        return (60.0 / data.toDoubleSafe()).timeDecimalToFormattedTime()
    }

    override fun toMiPerHour(data: String): String {
        return (data.toDoubleSafe() * KM_TO_MI).toString()
    }

    override fun toMinPerMi(data: String): String {
        return (60.0 / (0.621 * data.toDoubleSafe())).timeDecimalToFormattedTime()
    }
}
