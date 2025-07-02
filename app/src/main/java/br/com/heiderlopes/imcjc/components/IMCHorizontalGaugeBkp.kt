package br.com.heiderlopes.imcjc.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp

@Composable
fun IMCHorizontalGaugeBkp(
    modifier: Modifier = Modifier,
    imc: Float = 22f,
    showLabels: Boolean = false
) {
    val categories = listOf(
        "Severely\nUnderweight" to Color(0xFF007ACC),
        "Underweight" to Color(0xFF40C4FF),
        "Optimal" to Color(0xFF4CAF50),
        "Overweight" to Color(0xFFFFEB3B),
        "Obese" to Color(0xFFFF5722),
        "Severely\nObese" to Color(0xFFD32F2F)
    )

    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(horizontal = 16.dp)
    ) {
        val baseBarHeight = size.height * 0.25f
        val categoryWidth = size.width / categories.size
        val highlightHeight = baseBarHeight * 1.2f
        val baseBottom = size.height

        val index = when {
            imc < 16 -> 0
            imc < 18.5 -> 1
            imc < 24.9 -> 2
            imc < 29.9 -> 3
            imc < 34.9 -> 4
            else -> 5
        }

        val (label, selectedColor) = categories[index]

        // 1. Desenhar faixas normais centralizadas verticalmente em relação à faixa destacada
        categories.forEachIndexed { i, (_, color) ->
            if (i != index) {
                val top = baseBottom - (highlightHeight / 2f) - (baseBarHeight / 2f)
                drawRoundRect(
                    color = color,
                    topLeft = Offset(x = i * categoryWidth, y = top),
                    size = Size(width = categoryWidth, height = baseBarHeight),
                    cornerRadius = CornerRadius(12f, 12f)
                )
            }
        }

        // 2. Desenhar faixa destacada mais alta
        val highlightTop = baseBottom - highlightHeight
        drawRoundRect(
            color = selectedColor,
            topLeft = Offset(x = index * categoryWidth, y = highlightTop),
            size = Size(width = categoryWidth, height = highlightHeight),
            cornerRadius = CornerRadius(12f, 12f)
        )

        // 3. Indicador de posição
        val normalizedImc = imc.coerceIn(10f, 40f)
        val imcPositionX = ((normalizedImc - 10f) / 30f) * size.width

        drawLine(
            color = Color.DarkGray,
            start = Offset(imcPositionX, highlightTop),
            end = Offset(imcPositionX, baseBottom),
            strokeWidth = 3.dp.toPx()
        )
    }
}
