package br.com.heiderlopes.imcjc.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollSource.Companion.SideEffect
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val MaleLightColors = lightColorScheme(
    primary = Color(0xFF1565C0),
    onPrimary = Color.White,
    background = Color(0xFFE3F2FD),
    onBackground = Color.Black
)

private val MaleDarkColors = darkColorScheme(
    primary = Color(0xFF90CAF9),
    onPrimary = Color.Black,
    background = Color(0xFF121212),
    onBackground = Color.White
)

private val FemaleLightColors = lightColorScheme(
    primary = Color(0xFFD81B60),
    onPrimary = Color.White,
    background = Color(0xFFFCE4EC),
    onBackground = Color.Black
)

private val FemaleDarkColors = darkColorScheme(
    primary = Color(0xFFF48FB1),
    onPrimary = Color.Black,
    background = Color(0xFF121212),
    onBackground = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    secondary = secondaryLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
)

@Composable
fun IMCJCTheme(
    isMale: Boolean,
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        isMale && isDarkTheme -> MaleDarkColors
        isMale && !isDarkTheme -> MaleLightColors
        !isMale && isDarkTheme -> FemaleDarkColors
        else -> FemaleLightColors
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
//@Composable
//fun IMCJCTheme(
//    darkTheme: Boolean = isSystemInDarkTheme(),
//    // Dynamic color is available on Android 12+
//    dynamicColor: Boolean = false,
//    content: @Composable () -> Unit
//) {
//
//    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }
//
//        darkTheme -> DarkColorScheme
//        else -> LightColorScheme
//    }
//
//    val systemUiController = rememberSystemUiController()
//    val navigationBarColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
//    val statusBarColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
//
//    SideEffect {
//        systemUiController.setStatusBarColor(
//            color = colorScheme.primary,
//            darkIcons = !darkTheme
//        )
//
//        systemUiController.setNavigationBarColor(
//            color = colorScheme.primary,
//            darkIcons = !darkTheme
//        )
//    }
//
//    MaterialTheme(
//        colorScheme = colorScheme,
//        typography = Typography,
//        content = content
//    )
//}