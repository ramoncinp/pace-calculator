package ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import data.UnitOfMeasurement
import data.UnitOfMeasurementElement

@Composable
fun ChipGroup(
    units: List<UnitOfMeasurementElement>,
    selectedUnit: UnitOfMeasurement? = null,
    modifier: Modifier,
    speedRange: Int = 0,
    onSelectedChanged: (UnitOfMeasurement) -> Unit = {},
) {
    Column(modifier = modifier) {
        LazyRow {
            items(units) {
                ChipElement(
                    unit = it.unit,
                    isSelected = selectedUnit == it.unit,
                    speedRange = speedRange,
                    onSelectionChanged = { unit ->
                        onSelectedChanged(unit)
                    },
                )
            }
        }
    }
}
