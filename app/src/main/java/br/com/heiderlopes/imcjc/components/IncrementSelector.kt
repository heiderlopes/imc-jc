package br.com.heiderlopes.imcjc.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun IncrementSelector(
    modifier: Modifier = Modifier,
    label: String,
    value: Int,
    unit: String = "",
    onValueChange: (Int) -> Unit,
    minValue: Int = Int.MIN_VALUE,
    maxValue: Int = Int.MAX_VALUE
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.weight(1f))

            AnimatedIconButton(
                icon = Icons.Default.Remove,
                size = 32.dp,
                iconSize = 18.dp,
                contentDescription = "Aumentar",
                onClick = { onValueChange((value -1).coerceAtLeast(minValue)) }
            )

            Text(
                text = "$value $unit".trim(),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .width(100.dp)
                    .padding(horizontal = 16.dp)
            )

            AnimatedIconButton(
                icon = Icons.Default.Add,
                size = 32.dp,
                iconSize = 18.dp,
                contentDescription = "Aumentar",
                onClick = { onValueChange((value + 1).coerceAtMost(maxValue)) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IncrementSelectorPreview() {
    MaterialTheme {
        val peso = remember { mutableStateOf(70) }

        IncrementSelector(
            label = "Peso",
            value = peso.value,
            unit = "kg",
            onValueChange = { peso.value = it },
            minValue = 0,
            maxValue = 300
        )
    }
}
