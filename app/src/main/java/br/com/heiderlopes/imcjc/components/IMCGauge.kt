package br.com.heiderlopes.imcjc.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.cos
import kotlin.math.sin

//@Composable
//fun IMCGauge(
//    modifier: Modifier = Modifier,
//    imc: Float = 22f
//) {
//    val categories = listOf(
//        "Severely\nUnderweight" to Color(0xFF007ACC),
//        "Underweight" to Color(0xFF40C4FF),
//        "Optimal" to Color(0xFF4CAF50),
//        "Overweight" to Color(0xFFFFEB3B),
//        "Obese" to Color(0xFFFF5722),
//        "Severely\nObese" to Color(0xFFD32F2F)
//    )
//
//    val sweepAngle = 180f / categories.size
//
//    Canvas(
//        modifier = modifier
//            .fillMaxWidth()                // Usa toda a largura disponível
//            .aspectRatio(2f)               // Mantém proporção 2:1 (semicircular)
//    ) {
//        val canvasWidth = size.width
//        val canvasHeight = size.height
//
//        val centerX = canvasWidth / 2
//        val centerY = canvasHeight
//        val radius = size.minDimension / 1.8f // ajustado para se expandir mais
//
//        // Desenha arcos
//        categories.forEachIndexed { index, (_, color) ->
//            drawArc(
//                color = color,
//                startAngle = 180f + sweepAngle * index,
//                sweepAngle = sweepAngle,
//                useCenter = false,
//                style = Stroke(width = radius * 0.18f), // espessura proporcional
//                topLeft = Offset(centerX - radius, centerY - radius),
//                size = Size(radius * 2, radius * 2)
//            )
//        }
//
//        // Ponteiro
//        val index = when {
//            imc < 16 -> 0
//            imc < 18.5 -> 1
//            imc < 24.9 -> 2
//            imc < 29.9 -> 3
//            imc < 34.9 -> 4
//            else -> 5
//        }
//
//        val pointerAngle = Math.toRadians((180 + sweepAngle * index + sweepAngle / 2).toDouble())
//        val pointerLength = radius * 0.85f
//        val endX = (centerX + pointerLength * cos(pointerAngle)).toFloat()
//        val endY = (centerY + pointerLength * sin(pointerAngle)).toFloat()
//
//        drawLine(
//            color = Color.DarkGray,
//            start = Offset(centerX, centerY),
//            end = Offset(endX, endY),
//            strokeWidth = radius * 0.08f,
//            cap = StrokeCap.Round
//        )
//
//        drawCircle(
//            color = Color.DarkGray,
//            radius = radius * 0.12f,
//            center = Offset(centerX, centerY)
//        )
//
////        // Rótulos
////        val labelRadius = radius * 1.15f
////        val paint = android.graphics.Paint().apply {
////            color = android.graphics.Color.BLACK
////            textSize = radius * 0.14f
////            textAlign = android.graphics.Paint.Align.CENTER
////            isAntiAlias = true
////        }
////
////        categories.forEachIndexed { i, (label, _) ->
////            val angle = Math.toRadians((180 + sweepAngle * i + sweepAngle / 2).toDouble())
////            val x = centerX + labelRadius * cos(angle).toFloat()
////            val y = centerY + labelRadius * sin(angle).toFloat()
////
////            label.split("\n").forEachIndexed { lineIndex, line ->
////                drawContext.canvas.nativeCanvas.drawText(
////                    line,
////                    x,
////                    y + lineIndex * paint.textSize * 1.1f,
////                    paint
////                )
////            }
////        }
//    }
//}



import androidx.compose.ui.graphics.toArgb

@Composable
fun IMCGauge(
    modifier: Modifier = Modifier,
    imc: Float = 22f
) {
    val categories = listOf(
        "Severely\nUnderweight" to Color(0xFF007ACC),
        "Underweight" to Color(0xFF40C4FF),
        "Optimal" to Color(0xFF4CAF50),
        "Overweight" to Color(0xFFFFEB3B),
        "Obese" to Color(0xFFFF5722),
        "Severely\nObese" to Color(0xFFD32F2F)
    )

    val sweepAngle = 180f / categories.size

    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    ) {
        val radius = size.minDimension / 1.8f
        val centerX = size.width / 2f
        val centerY = size.height
        val arcStroke = radius * 0.18f

        // 1. Desenha todos os arcos
        categories.forEachIndexed { index, (_, color) ->
            drawArc(
                color = color,
                startAngle = 180f + sweepAngle * index,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(width = arcStroke),
                topLeft = Offset(centerX - radius, centerY - radius),
                size = Size(radius * 2, radius * 2)
            )
        }

        // 2. Índice do arco a ser destacado
        val index = when {
            imc < 16 -> 0
            imc < 18.5 -> 1
            imc < 24.9 -> 2
            imc < 29.9 -> 3
            imc < 34.9 -> 4
            else -> 5
        }

        // 3. Destaca o arco selecionado e pega a label e cor
        var (label, color) = categories[index]

        drawArc(
            color = color.copy(alpha = 1f),
            startAngle = 180f + sweepAngle * index,
            sweepAngle = sweepAngle,
            useCenter = false,
            style = Stroke(
                width = arcStroke * 1.4f,
                cap = StrokeCap.Round
            ),
            topLeft = Offset(centerX - radius, centerY - radius),
            size = Size(radius * 2, radius * 2)
        )

        val canvas = drawContext.canvas.nativeCanvas

        val valuePaint = android.graphics.Paint().apply {
            color = Color.White
            textSize = radius * 0.40f
            textAlign = android.graphics.Paint.Align.CENTER
            isFakeBoldText = true
            isAntiAlias = true
        }

        val labelPaint = android.graphics.Paint().apply {
            textSize = radius * 0.20f
            textAlign = android.graphics.Paint.Align.CENTER
            isAntiAlias = true
            //color = color.toArgb()
        }

        // IMC valor no topo
        canvas.drawText("%.1f".format(imc), centerX, centerY - radius * 0.2f, valuePaint)

        // Label da categoria abaixo do valor
        label.split("\n").forEachIndexed { i, line ->
            canvas.drawText(
                line,
                centerX,
                centerY - radius * 0.3f + valuePaint.textSize * 1f + i * labelPaint.textSize * 1.2f,
                labelPaint
            )
        }
    }
}
