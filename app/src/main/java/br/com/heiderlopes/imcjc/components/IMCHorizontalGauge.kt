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
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import kotlin.math.floor

@Composable
fun IMCHorizontalGauge(
    modifier: Modifier = Modifier,
    imc: Float = 22f,
    showLabels: Boolean = false
) {
    val primaryColor = MaterialTheme.colorScheme.primary

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

        val spacePx = 4.dp.toPx() // Espaçamento desejado entre as faixas

        // Calcular a largura disponível descontando os espaços entre faixas
        val totalSpacing = spacePx * (categories.size - 1)
        val adjustedCategoryWidth = (size.width - totalSpacing) / categories.size

        // Desenhar faixas normais com espaçamento
        categories.forEachIndexed { i, (_, color) ->
            if (i != index) {
                val top = baseBottom - (highlightHeight / 2f) - (baseBarHeight / 2f)
                val left = i * (adjustedCategoryWidth + spacePx) // deslocamento X com espaçamento
                drawRoundRect(
                    color = color,
                    topLeft = Offset(x = left, y = top),
                    size = Size(width = adjustedCategoryWidth, height = baseBarHeight),
                    cornerRadius = CornerRadius(12f, 12f)
                )
            }
        }

        // Faixa destacada (com mesmo espaçamento)
        val highlightTop = baseBottom - highlightHeight
        val highlightLeft = index * (adjustedCategoryWidth + spacePx)
        val borderWidth = 3.dp.toPx()

        // 1. Desenha o retângulo preenchido
        drawRoundRect(
            color = selectedColor,
            topLeft = Offset(x = highlightLeft, y = highlightTop),
            size = Size(adjustedCategoryWidth, highlightHeight),
            cornerRadius = CornerRadius(12f, 12f)
        )

        // 2. Desenha a borda em cima (contorno)
        drawRoundRect(
            color = primaryColor, // cor da borda (pode mudar)
            topLeft = Offset(x = highlightLeft, y = highlightTop),
            size = Size(adjustedCategoryWidth, highlightHeight),
            cornerRadius = CornerRadius(12f, 12f),
            style = Stroke(width = borderWidth)
        )
    }
}
