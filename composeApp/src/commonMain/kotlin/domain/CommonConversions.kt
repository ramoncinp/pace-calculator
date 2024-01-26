package domain

const val HOUR_TO_MINUTE = 60.0
const val KM_TO_MI = 0.621

// Ej. 8.5 => 8'30"
fun Double.timeDecimalToFormattedTime(): String {
    val hour: Int = if (this > 60.0) (this / 60).toInt() else 0
    val minutes = this.toInt() - (hour * 60)
    val seconds = ((this - this.toInt()) * 60).toInt()
    return if (hour == 0) {
        "$minutes'$seconds\""
    } else {
        "${hour}h$minutes'$seconds\""
    }
}

fun Char.toDecValue(): Int {
    return (this - 0x30).code
}

fun String.toDoubleSafe() = try {
    this.toDouble()
} catch (e: Exception) {
    -0.1
}
