package com.example.ui.theme

import androidx.compose.ui.graphics.Color

// Joyful Child-Friendly Colors
val JoyfulCoral = Color(0xFFFF5252)
val JoyfulAmber = Color(0xFFFFB300)
val JoyfulSkyBlue = Color(0xFF2979FF)
val JoyfulMint = Color(0xFF00E676)
val JoyfulLavender = Color(0xFFB388FF)
val JoyfulPink = Color(0xFFFF4081)

val WarmCreamBg = Color(0xFFFFFDF8)
val WarmCardBg = Color(0xFFFFFFFF)
val SoftSurfaceVariant = Color(0xFFF6F8FA)

val DarkTextPrimary = Color(0xFF2D3748)
val DarkTextSecondary = Color(0xFF718096)

// Soft Pastel Atmosphere Colors
val PastelCream = Color(0xFFFFFDF7)
val PastelBabyBlue = Color(0xFFF0F7FF)
val PastelSoftPink = Color(0xFFFFF0F5)
val PastelSoftYellow = Color(0xFFFFFDE7)
val PastelMint = Color(0xFFF0FFF4)
val PastelLavender = Color(0xFFF5EEFD)
val PastelPeach = Color(0xFFFFEFE6)

// Category Card Theme Tints & Accents
object CategoryThemeColors {
    data class Style(
        val bg: Color,
        val border: Color,
        val accent: Color,
        val pillBg: Color,
        val pillText: Color
    )

    val Animals = Style(
        bg = Color(0xFFFFF5EE),
        border = Color(0xFFFFD7C7),
        accent = Color(0xFFFF7043),
        pillBg = Color(0xFFFFE0B2),
        pillText = Color(0xFFD84315)
    )

    val Vehicles = Style(
        bg = Color(0xFFF0F8FF),
        border = Color(0xFFBAE6FD),
        accent = Color(0xFF0288D1),
        pillBg = Color(0xFFE0F2FE),
        pillText = Color(0xFF0369A1)
    )

    val Dinosaurs = Style(
        bg = Color(0xFFF0FBF4),
        border = Color(0xFFBBF7D0),
        accent = Color(0xFF2E7D32),
        pillBg = Color(0xFFDCFCE7),
        pillText = Color(0xFF15803D)
    )

    val Fruits = Style(
        bg = Color(0xFFFFF0F5),
        border = Color(0xFFFBCFE8),
        accent = Color(0xFFE91E63),
        pillBg = Color(0xFFFCE7F3),
        pillText = Color(0xFFBE185D)
    )

    val Nature = Style(
        bg = Color(0xFFFFFDEB),
        border = Color(0xFFFEF08A),
        accent = Color(0xFFF57C00),
        pillBg = Color(0xFFFEF9C3),
        pillText = Color(0xFFA16207)
    )

    val Space = Style(
        bg = Color(0xFFF7F0FD),
        border = Color(0xFFE9D5FF),
        accent = Color(0xFF8E24AA),
        pillBg = Color(0xFFF3E8FF),
        pillText = Color(0xFF6B21A8)
    )

    val Toys = Style(
        bg = Color(0xFFFFF8EC),
        border = Color(0xFFFED7AA),
        accent = Color(0xFFFF8F00),
        pillBg = Color(0xFFFFEDD5),
        pillText = Color(0xFFC2410C)
    )

    val Alphabet = Style(
        bg = Color(0xFFEDFCFD),
        border = Color(0xFFA5F3FC),
        accent = Color(0xFF0097A7),
        pillBg = Color(0xFFCFFAFE),
        pillText = Color(0xFF0E7490)
    )
}

