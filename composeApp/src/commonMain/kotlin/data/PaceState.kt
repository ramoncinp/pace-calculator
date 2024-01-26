package data

data class PaceState(
    val result: String = "",
    val input: String = "",
    val speeds: Speeds = Speeds(),
    val raceTimes: Times = Times(),
    val speedRange: Int = 0,
    val inputEnabled: Boolean = true,
    val units: List<UnitOfMeasurementElement> = getUnitsOfMeasurement(),
    val selectedUnitOfMeasurement: UnitOfMeasurement = UnitOfMeasurement.MINUTE_PER_KM
)

data class Speeds(
    val kmPerHour: String = "",
    val miPerHour: String = "",
    val minPerKm: String = "",
    val minPerMi: String = ""
)

data class Times(
    val fiveKm: String = "",
    val tenKm: String = "",
    val halfMarathon: String = "",
    val marathon: String = ""
)
