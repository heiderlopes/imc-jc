package br.com.heiderlopes.imcjc.screen.imc

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import br.com.heiderlopes.imcjc.R
import br.com.heiderlopes.imcjc.screen.imc.layout.landscape.IMCLandscape
import br.com.heiderlopes.imcjc.screen.imc.layout.portrait.IMCPortrait

@Composable
fun IMCScreen(
    modifier: Modifier,
    isMale: Boolean,
    onGenderChange: (Boolean) -> Unit
) {

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    var peso by remember { mutableStateOf(75) }
    var altura by remember { mutableStateOf(175) }

    val alturaM = altura / 100f
    val imc = if (alturaM > 0) peso / (alturaM * alturaM) else 0f

//    val res = when {
//        imc < 18.5 -> Pair(R.string.imc_magreza, R.drawable.imc_2_magreza)
//        imc < 25 -> Pair(R.string.imc_ideal, R.drawable.imc_2_peso_ideal)
//        imc < 30 -> Pair(R.string.imc_sobrepeso, R.drawable.imc_2_sobrepeso)
//        imc < 40 -> Pair(R.string.imc_obeso, R.drawable.imc_2_obesidade)
//        else -> Pair(R.string.imc_obeso_ii, R.drawable.imc_2_obesidade_2)
//    }
    val res = when {
        imc < 18.5 -> Triple(R.string.imc_magreza, R.drawable.imc_2_h_magreza, R.drawable.imc_2_m_magreza)
        imc < 25 -> Triple(R.string.imc_ideal, R.drawable.imc_2_h_peso_ideal, R.drawable.imc_2_m_ideal)
        imc < 30 -> Triple(R.string.imc_sobrepeso, R.drawable.imc_2_h_sobrepeso, R.drawable.imc_2_m_sobrepeso)
        imc < 40 -> Triple(R.string.imc_obeso, R.drawable.imc_2_h_obesidade, R.drawable.imc_2_m_obesidade)
        else -> Triple(R.string.imc_obeso_ii, R.drawable.imc_2_h_obesidade_2, R.drawable.imc_2_m_obesidade_2)
    }

    val (stringRes, imageMRes, imageFRes) = res

    if (isLandscape) {
        IMCLandscape(
            modifier = modifier,
            peso = peso,
            onChangePeso = { peso = it },
            altura = altura,
            onChangeAltura = { altura = it },
            imc = imc,
            imageRes = if(isMale)imageMRes else imageFRes,
            stringRes = stringRes,
            isMale = isMale,
            onGenderChange = onGenderChange
        )
    } else {
        IMCPortrait(
            modifier = modifier,
            peso = peso,
            onChangePeso = { peso = it },
            altura = altura,
            onChangeAltura = { altura = it },
            imc = imc,
            imageRes = if(isMale)imageMRes else imageFRes,
            stringRes = stringRes,
            isMale = isMale,
            onGenderChange = onGenderChange
        )
    }
}