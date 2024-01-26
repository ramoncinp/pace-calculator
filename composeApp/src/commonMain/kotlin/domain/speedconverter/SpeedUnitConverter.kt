package domain.speedconverter

interface SpeedUnitConverter {
    fun toKmPerHour(data: String): String
    fun toMinPerKm(data: String): String
    fun toMiPerHour(data: String): String
    fun toMinPerMi(data: String): String
}
