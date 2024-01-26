package ui

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun PaceCalculatorTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = MaterialTheme.colors.copy(
            surface = LightBlue800,
            onSurface = Color.White,
            primary = LightBlue800,
            primaryVariant = LightBlue900,
            secondary = Color.Black
        )
    ) {
        content()
    }
}
