package com.arnix.typey.ui.theme.compose

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = TypeyPrimaryDark,
    onPrimary = Color(0xFF0F172A),
    primaryContainer = TypeyPrimaryContainerDark,
    onPrimaryContainer = Color(0xFFDBEAFE),
    secondary = TypeySecondaryDark,
    onSecondary = Color(0xFF0F172A),
    tertiary = TypeyPrimaryDark,
    onTertiary = Color(0xFF0F172A),
    background = TypeyBackgroundDark,
    onBackground = TypeyTextDark,
    surface = TypeySurfaceDark,
    onSurface = TypeyTextDark,
    surfaceVariant = TypeySurfaceVariantDark,
    onSurfaceVariant = TypeyTextSecondaryDark,
    outline = TypeyBorderDark,
    outlineVariant = TypeyDividerDark
)

private val LightColorScheme = lightColorScheme(
    primary = TypeyPrimary,
    onPrimary = Color.White,
    primaryContainer = TypeyPrimaryContainer,
    onPrimaryContainer = Color(0xFF1E3A8A),
    secondary = TypeySecondary,
    onSecondary = Color.White,
    tertiary = TypeyPrimaryVariant,
    onTertiary = Color.White,
    background = TypeyBackground,
    onBackground = TypeyText,
    surface = TypeySurface,
    onSurface = TypeyText,
    surfaceVariant = TypeySurfaceVariantLight,
    onSurfaceVariant = TypeyTextSecondary,
    outline = TypeyBorder,
    outlineVariant = TypeyDivider
)

@Composable
fun TypeyKeyboardTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
