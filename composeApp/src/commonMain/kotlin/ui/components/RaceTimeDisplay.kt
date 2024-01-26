package ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp

@Composable
fun RaceTimeDisplay(title: String, time: String) {
    Box(
        modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
    ) {
        Column {
            Text(
                text = title,
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                fontSize = TextUnit(12f, TextUnitType.Sp),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onSurface
            )
            Text(
                text = time,
                modifier = Modifier.fillMaxWidth(),
                fontSize = TextUnit(32f, TextUnitType.Sp),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.onSurface
            )
        }
    }
}
