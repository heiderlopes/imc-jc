package br.com.heiderlopes.imcjc.screen.imc.layout.landscape

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.heiderlopes.imcjc.components.IMCGauge
import br.com.heiderlopes.imcjc.components.IMCHorizontalGauge

@Composable
fun IMCLandscapeLeftPanel(
    modifier: Modifier = Modifier,
    @DrawableRes imageRes: Int,
    @StringRes stringRes: Int,
    imc: Float
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
//            .background(
//                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
//                shape = RoundedCornerShape(16.dp)
//            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Row(
            modifier = modifier
                .fillMaxSize()
                .padding(end = 16.dp),
            verticalAlignment = Alignment.CenterVertically, // centraliza verticalmente
            horizontalArrangement = Arrangement.Center      // centraliza horizontalmente
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)             // ocupa metade do espaço disponível
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally, // centraliza textos na coluna
                verticalArrangement = Arrangement.Center             // centraliza verticalmente na coluna
            ) {
                Text(
                    text = "%.1f".format(imc),
                    style = MaterialTheme.typography.headlineMedium,
                    fontSize = 64.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = "IMC: ${stringResource(stringRes).uppercase()}",
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.secondary
                )
            }

            Crossfade(
                animationSpec = tween(durationMillis = 600),
                targetState = imageRes,
                label = "IMC Image Transition",
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
            ) { resId ->
                Image(
                    painter = painterResource(resId),
                    contentDescription = "Personagem IMC ${stringRes}",
                    modifier = Modifier.fillMaxWidth() // ocupa toda a largura do peso 1f
                )
            }
        }
    }


//    Row(
//        modifier = modifier
//            .fillMaxSize()
//            .padding(end = 16.dp),
//        verticalAlignment = Alignment.Bottom,
//        horizontalArrangement = Arrangement.SpaceBetween
//    ) {
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .fillMaxHeight(),
//            contentAlignment = Alignment.Center
//        ) {
//            Column(
//                modifier = Modifier.fillMaxSize()
//            ) {
//                IMCHorizontalGauge(
//                    imc = imc,
//                )
//
//                Crossfade(
//                    animationSpec = tween(durationMillis = 600),
//                    targetState = imageRes,
//                    label = "IMC Image Transition"
//                ) { resId ->
//                    Image(
//                        painter = painterResource(resId),
//                        contentDescription = stringResource(stringRes),
//                        modifier = Modifier
//                            .fillMaxWidth(0.8f)
//                            .aspectRatio(1f)
//                    )
//                }
//
//
//            }
//
//
//            IMCGauge(
//                imc = imc,
//                modifier = Modifier
//                    .width(120.dp)
//                    .height(120.dp)
//                    .align(Alignment.BottomStart) // 👈 posiciona no canto inferior esquerdo
//            )
//        }
//    }
}