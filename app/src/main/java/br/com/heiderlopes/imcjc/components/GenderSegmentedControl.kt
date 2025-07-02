package br.com.heiderlopes.imcjc.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun GenderSegmentedControl(
    selectedGender: String,
    onGenderSelected: (String) -> Unit
) {
    val genders = listOf("Homem", "Mulher")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        genders.forEach { gender ->
            val isSelected = gender == selectedGender
//            Text(
//                text = gender,
//                modifier = Modifier
//                    .weight(1f)
//                    .padding(4.dp)
//                    .clip(RoundedCornerShape(20.dp))
//                    .background(if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface)
//                    .clickable { onGenderSelected(gender) }
//                    .padding(vertical = 12.dp),
//                color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
//                textAlign = TextAlign.Center
//            )
            Text(
                text = gender,
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface)
                    .clickable { onGenderSelected(gender) }
                    .padding(vertical = 12.dp),
                color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )
        }
    }
}
