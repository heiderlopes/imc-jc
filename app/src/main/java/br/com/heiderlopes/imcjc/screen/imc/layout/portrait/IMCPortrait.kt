package br.com.heiderlopes.imcjc.screen.imc.layout.portrait

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.heiderlopes.imcjc.components.GenderSegmentedControl
import br.com.heiderlopes.imcjc.components.IncrementSelector

@Composable
fun IMCPortrait(
    modifier: Modifier = Modifier,
    peso: Int,
    onChangePeso: (Int) -> Unit,
    altura: Int,
    onChangeAltura: (Int) -> Unit,
    imc: Float,
    @DrawableRes imageRes: Int,
    @StringRes stringRes: Int
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Crossfade(
            animationSpec = tween(durationMillis = 600),
            targetState = imageRes, label = "IMC Image Transition", modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) { resId ->
            Image(
                painter = painterResource(resId),
                contentDescription = "Personagem IMC ${stringRes}",
                modifier = Modifier
                    .weight(1f)
            )
        }

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

//        IMCHorizontalGauge(
//            imc = imc,
//            //showLabels = false
//        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
//                .background(
//                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
//                    shape = RoundedCornerShape(16.dp)
//                )
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Text(
                "Conte mais sobre você",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
                    .padding(16.dp)
            )


            var gender by remember { mutableStateOf("Homem") }

            GenderSegmentedControl(
                selectedGender = gender,
                onGenderSelected = { gender = it }
            )

            IncrementSelector(
                label = "Peso",
                value = peso,
                unit = "kg",
                onValueChange = { onChangePeso(it) },
                minValue = 0,
                maxValue = 200
            )

            IncrementSelector(
                label = "Altura",
                value = altura,
                unit = "cm",
                onValueChange = { onChangeAltura(it) },
                minValue = 0,
                maxValue = 220
            )
        }

    }
}