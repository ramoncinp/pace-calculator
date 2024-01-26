package data

enum class UnitOfMeasurement {
    MINUTE_PER_KM,
    KM_PER_HOUR,
    MILES_PER_HOUR,
    MINUTE_PER_MILE
}

data class UnitOfMeasurementElement(
    val title: String,
    val unit: UnitOfMeasurement
)

fun getUnitsOfMeasurement() = UnitOfMeasurement.entries.map { it.toElement() }

fun UnitOfMeasurement.toElement(): UnitOfMeasurementElement {
    val title = when (this) {
        UnitOfMeasurement.MINUTE_PER_KM -> "min/Km"
        UnitOfMeasurement.KM_PER_HOUR -> "km/h"
        UnitOfMeasurement.MILES_PER_HOUR -> "mi/h"
        UnitOfMeasurement.MINUTE_PER_MILE -> "min/mi"
    }
    return UnitOfMeasurementElement(title, this)
}
