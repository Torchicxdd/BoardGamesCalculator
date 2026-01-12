package com.example.redrisingcalculator.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryColourDark,
    onPrimary = TextColourPrimaryDark,
    secondary = SecondaryColourDark,
    onSecondary = TextColourPrimaryDark,
    tertiary = TertiaryColourDark,
    onTertiary = TextColourPrimaryDark,
    surface = BackgroundDark,
    onSurface = TextColourPrimaryDark,
    background = BackgroundDark,
    onBackground = TextColourPrimaryDark
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryColourLight,
    onPrimary = TextColourPrimaryDark,
    secondary = SecondaryColourLight,
    onSecondary = TextColourPrimaryLight,
    tertiary = TertiaryColourLight,
    onTertiary = TextColourPrimaryLight,
    surface = BackgroundLight,
    onSurface = TextColourPrimaryLight,
    background = BackgroundLight,
    onBackground = TextColourPrimaryLight
)

@Composable
fun RedRisingCalculatorTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (useDarkTheme) DarkColorScheme else LightColorScheme
    val extraColors = if (useDarkTheme) DarkExtraColors else LightExtraColors

    CompositionLocalProvider(
        LocalExtraColors provides extraColors
    ) {
        MaterialTheme(
            colorScheme = colors,
            typography = Typography,
            content = content
        )
    }
}