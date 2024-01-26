import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import data.PaceState
import data.UnitOfMeasurement
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import ui.CalculatorEvent
import ui.PaceCalculatorTheme
import ui.components.ChipGroup
import ui.components.RaceTimeDisplay
import ui.getSurfaceColor
import viewmodel.PaceCalculatorViewModel

@Composable
fun App() {
    PaceCalculatorTheme {
        PacePageContent()
    }
}

@Composable
fun PacePageContent(viewModel: PaceCalculatorViewModel = getVewModelInstance()) {
    val state by viewModel.paceState.collectAsState()
    Box(
        modifier = Modifier
            .background(getSurfaceColor(state.speedRange))
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column {
            UnitsChipGroup(state, modifier = Modifier.padding(bottom = 16.dp)) {
                viewModel.onEvent(
                    CalculatorEvent.OnUnitOfMeasurementChanged(it)
                )
            }
            InputBox(
                state,
                {
                    viewModel.onEvent(CalculatorEvent.InputChanged(it))
                },
                {
                    viewModel.onEvent(CalculatorEvent.ClearInput)
                }
            )
            if (state.result.isNotEmpty()) {
                RacesTimes(state)
            }
        }
    }
}

@Composable
fun UnitsChipGroup(
    state: PaceState,
    modifier: Modifier = Modifier,
    onSelectedChanged: (UnitOfMeasurement) -> Unit
) {
    ChipGroup(
        units = state.units,
        selectedUnit = state.selectedUnitOfMeasurement,
        modifier = modifier,
        speedRange = state.speedRange,
        onSelectedChanged = onSelectedChanged
    )
}

@Composable
fun InputBox(state: PaceState, onInputChanged: (String) -> Unit, onInputCleared: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        CurrentPaceField(
            input = state.result,
            isEnabled = state.inputEnabled,
            onInputChanged = onInputChanged,
            speedRange = state.speedRange,
            modifier = Modifier.weight(1f)
        )
        IconButton(
            onClick = onInputCleared,
            modifier = Modifier.padding(start = 12.dp)
        ) {
            Icon(
                Icons.Filled.Close,
                tint = MaterialTheme.colors.onSurface,
                contentDescription = null
            )
        }
    }
}

@Composable
fun CurrentPaceField(
    input: String,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    speedRange: Int,
    onInputChanged: (String) -> Unit
) {
    val textVal = TextFieldValue(input, selection = TextRange(input.length))
    TextField(
        modifier = modifier,
        value = textVal,
        enabled = isEnabled,
        onValueChange = { onInputChanged.invoke(it.text) },
        textStyle = TextStyle(
            fontSize = TextUnit(24f, TextUnitType.Sp),
            color = MaterialTheme.colors.onSurface,
            textAlign = TextAlign.Center
        ),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = getSurfaceColor(speedRange),
            cursorColor = Color.White,
            focusedIndicatorColor = Color.White
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        )
    )
}

@Composable
fun RacesTimes(state: PaceState) {
    val times = state.raceTimes
    Column(modifier = Modifier.padding(top = 24.dp)) {
        RaceTimeDisplay("5km", times.fiveKm)
        RaceTimeDisplay("10km", times.tenKm)
        RaceTimeDisplay("Half Marathon (21km)", times.halfMarathon)
        RaceTimeDisplay("Marathon (42km)", times.marathon)
    }
}

@Composable
fun getVewModelInstance(): PaceCalculatorViewModel {
    return getViewModel(
        Unit,
        viewModelFactory { PaceCalculatorViewModel() }
    )
}
