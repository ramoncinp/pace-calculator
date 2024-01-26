package ui

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val LightBlue800 = Color(0xFF0277BD)
val LightBlue900 = Color(0xFF01579B)

@Composable
fun getSurfaceColor(speedRange: Int): Color {
    return when (speedRange) {
        1 -> Color(0xFF1B5E20)
        2 -> Color(0xFF2E7D32)
        3 -> Color(0xFFFFD600)
        4 -> Color(0xFFFF8F00)
        5 -> Color(0xFFD84315)
        6 -> Color(0xFFBF360C)
        else -> MaterialTheme.colors.surface
    }
}
