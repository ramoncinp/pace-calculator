package ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import data.UnitOfMeasurement
import data.toElement
import ui.getSurfaceColor

@Composable
fun ChipElement(
    unit: UnitOfMeasurement,
    isSelected: Boolean = false,
    speedRange: Int = 0,
    onSelectionChanged: (UnitOfMeasurement) -> Unit = {}
) {
    Surface(
        border = BorderStroke(1.dp, color = Color.White),
        modifier = Modifier.padding(4.dp).toggleable(
            value = isSelected,
            onValueChange = {
                onSelectionChanged(unit)
            }
        ),
        elevation = 8.dp,
        shape = RoundedCornerShape(24.dp),
        color = if (isSelected) MaterialTheme.colors.secondary else getSurfaceColor(speedRange)
    ) {
        Text(
            text = unit.toElement().title,
            style = MaterialTheme.typography.body2,
            color = Color.White,
            modifier = Modifier.padding(12.dp)
        )
    }
}
