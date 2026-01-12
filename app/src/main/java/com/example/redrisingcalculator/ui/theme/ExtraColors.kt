package com.example.redrisingcalculator.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class ExtraColors(
    val tertiaryBorder: Color,
)

// Light
val LightExtraColors = ExtraColors(
    tertiaryBorder = Color(0xFFB4B4B4)
)

// Dark
val DarkExtraColors = ExtraColors(
    tertiaryBorder = Color(0xFF2C2C2C)
)

val LocalExtraColors = staticCompositionLocalOf { LightExtraColors }