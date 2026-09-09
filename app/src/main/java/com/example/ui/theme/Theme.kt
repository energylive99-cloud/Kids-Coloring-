package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val KidsLightColorScheme = lightColorScheme(
    primary = JoyfulCoral,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFFFEBEE),
    onPrimaryContainer = Color(0xFFB71C1C),
    secondary = JoyfulAmber,
    onSecondary = Color(0xFF3E2723),
    secondaryContainer = Color(0xFFFFF8E1),
    onSecondaryContainer = Color(0xFFFF6F00),
    tertiary = JoyfulSkyBlue,
    onTertiary = Color.White,
    background = WarmCreamBg,
    onBackground = DarkTextPrimary,
    surface = WarmCardBg,
    onSurface = DarkTextPrimary,
    surfaceVariant = SoftSurfaceVariant,
    onSurfaceVariant = DarkTextSecondary
)

@Composable
fun KidsColoringTheme(
    darkTheme: Boolean = false, // Always keep cheerful bright light theme for kids
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = KidsLightColorScheme,
        typography = Typography,
        content = content
    )
}
