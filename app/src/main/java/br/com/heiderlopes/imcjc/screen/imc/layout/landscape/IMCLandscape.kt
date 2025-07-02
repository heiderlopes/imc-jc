package br.com.heiderlopes.imcjc.screen.imc.layout.landscape

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.heiderlopes.imcjc.components.GenderSegmentedControl
import br.com.heiderlopes.imcjc.components.IncrementSelector


@Composable
fun IMCLandscape(
    modifier: Modifier = Modifier,
    peso: Int,
    onChangePeso: (Int) -> Unit,
    altura: Int,
    onChangeAltura: (Int) -> Unit,
    imc: Float,
    @DrawableRes imageRes: Int,
    @StringRes stringRes: Int
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
            .padding(16.dp)
    ) {

        IMCLandscapeLeftPanel(
            modifier = modifier
                .weight(1f),
            imageRes = imageRes,
            stringRes = stringRes,
            imc
        )

        RightPanel(
            modifier = Modifier
                .weight(1f),
            peso = peso,
            onChangePeso = onChangePeso,
            altura = altura,
            onChangeAltura = onChangeAltura,
            stringRes = stringRes,
            imc = imc
        )
    }
}

@Composable
private fun RightPanel(
    modifier: Modifier = Modifier,
    peso: Int,
    onChangePeso: (Int) -> Unit,
    altura: Int,
    onChangeAltura: (Int) -> Unit,
    @StringRes stringRes: Int,
    imc: Float,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()

//            .background(
//                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
//                shape = RoundedCornerShape(16.dp)
//            ).padding(start = 16.dp)
        ,
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {

            var gender by remember { mutableStateOf("Homem") }

            Text(
                "Conte mais sobre você",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            GenderSegmentedControl(
                selectedGender = gender,
                onGenderSelected = { gender = it }
            )

            IncrementSelector(
                label = "Peso",
                value = peso,
                unit = "kg",
                onValueChange = onChangePeso,
                minValue = 0,
                maxValue = 200
            )

            IncrementSelector(
                label = "Altura",
                value = altura,
                unit = "cm",
                onValueChange = onChangeAltura,
                minValue = 0,
                maxValue = 220
            )

            //Text("IMC: $imc", fontSize = 20.sp, modifier = Modifier.padding(16.dp))

//            Text(
//                text = "%.1f".format(imc),
//                style = MaterialTheme.typography.headlineMedium,
//                fontSize = 64.sp,
//                fontWeight = FontWeight.Bold,
//                color = MaterialTheme.colorScheme.primary
//            )
//            Text(
//                text = "Seu IMC",
//                style = MaterialTheme.typography.bodyMedium,
//                fontSize = 14.sp,
//                color = MaterialTheme.colorScheme.onSurfaceVariant
//            )
//            Text(
//                text = stringResource(stringRes),
//                style = MaterialTheme.typography.titleMedium,
//                fontSize = 18.sp,
//                fontWeight = FontWeight.SemiBold,
//                color = MaterialTheme.colorScheme.secondary
//            )
        }
    }
}


//@Composable
//fun IMCLandscape(
//    modifier: Modifier = Modifier,
//    peso: Int,
//    onChangePeso: (Int) -> Unit,
//    altura: Int,
//    onChangeAltura: (Int) -> Unit,
//    imc: Float,
//    @DrawableRes imageRes: Int,
//    @StringRes stringRes: Int
//) {
//    Row(
//        modifier = modifier
//            .fillMaxSize()
//            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
//            .padding(16.dp)
//    ) {
//        LeftPanel(modifier = modifier.weight(1f), imageRes, stringRes)
//
//        RightPanel(
//            modifier = Modifier.weight(1f),
//            peso = peso,
//            onChangePeso = onChangePeso,
//            altura = altura,
//            onChangeAltura = { onChangeAltura(it) },
//            stringRes = stringRes,
//            imc = imc,
//        )
//    }
//}
//
//@Composable
//private fun LeftPanel(
//    modifier: Modifier = Modifier,
//    @DrawableRes imageRes: Int,
//    @StringRes stringRes: Int
//) {
//
//    Column(
//        modifier = modifier
//            .fillMaxWidth()
//            .padding(end = 8.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//
//        Crossfade(
//            animationSpec = tween(durationMillis = 600),
//            targetState = imageRes, label = "IMC Image Transition", modifier = Modifier
//                .weight(1f)
//                .padding(16.dp)
//        ) { resId ->
//            Image(
//                painter = painterResource(resId),
//                contentDescription = "Personagem IMC ${stringRes}",
//                modifier = Modifier
//                    .weight(1f)
//            )
//        }
//    }
//}
//
//@Composable
//private fun RightPanel(
//    modifier: Modifier = Modifier,
//    peso: Int,
//    onChangePeso: (Int) -> Unit,
//    altura: Int,
//    onChangeAltura: (Int) -> Unit,
//    @StringRes stringRes: Int,
//    imc: Float,
//) {
//    Column(
//        modifier = modifier
//            .padding(start = 8.dp)
//    ) {
//        Column(
//            modifier = Modifier
//                .weight(1f)
//                .fillMaxWidth(),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//
//            Text(
//                "%.1f".format(imc),
//                style = MaterialTheme.typography.titleMedium,
//                fontSize = 64.sp
//            )
//            Text(
//                "Seu IMC",
//                style = MaterialTheme.typography.titleMedium,
//                fontSize = 12.sp
//            )
//
//            //Text(stringResource(stringRes), style = MaterialTheme.typography.titleMedium)
//
//        }
//        IncrementSelector(
//            label = "Peso",
//            value = peso,
//            unit = "kg",
//            onValueChange = { onChangePeso(it) },
//            minValue = 0,
//            maxValue = 200
//        )
//
//        IncrementSelector(
//            label = "Altura",
//            value = altura,
//            unit = "cm",
//            onValueChange = { onChangeAltura(it) },
//            minValue = 0,
//            maxValue = 220
//        )
//    }
//}