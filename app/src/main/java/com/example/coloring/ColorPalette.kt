package com.example.coloring

import androidx.compose.ui.graphics.Color

object ColorPalette {

    data class NamedColor(
        val name: String,
        val color: Color
    )

    val defaultPalette = listOf(
        NamedColor("Red", Color(0xFFFF3B30)),
        NamedColor("Orange", Color(0xFFFF9500)),
        NamedColor("Yellow", Color(0xFFFFCC00)),
        NamedColor("Green", Color(0xFF34C759)),
        NamedColor("Light Green", Color(0xFF76FF03)),
        NamedColor("Blue", Color(0xFF007AFF)),
        NamedColor("Light Blue", Color(0xFF5AC8FA)),
        NamedColor("Purple", Color(0xFFAF52DE)),
        NamedColor("Pink", Color(0xFFFF2D55)),
        NamedColor("Brown", Color(0xFFA2845E)),
        NamedColor("Black", Color(0xFF1C1C1E)),
        NamedColor("White", Color(0xFFFFFFFF)),
        NamedColor("Gray", Color(0xFF8E8E93))
    )

    // Extended kid-friendly colors for the "+" dialog
    val extraColors = listOf(
        Color(0xFFFF80AB), // Pastel Rose
        Color(0xFFEA80FC), // Light Violet
        Color(0xFFB388FF), // Lavender
        Color(0xFF8C9EFF), // Periwinkle
        Color(0xFF82B1FF), // Baby Blue
        Color(0xFF80D8FF), // Sky Aqua
        Color(0xFF84FFFF), // Cyan Ice
        Color(0xFFA7FFEB), // Mint
        Color(0xFFB9F6CA), // Soft Leaf
        Color(0xFFCCFF90), // Lime Pastel
        Color(0xFFFFFF8D), // Butter Yellow
        Color(0xFFFFE57F), // Warm Cream
        Color(0xFFFFD180), // Peach
        Color(0xFFFF9E80), // Apricot
        Color(0xFFFF5252), // Coral
        Color(0xFFFF4081), // Magenta
        Color(0xFFE040FB), // Electric Purple
        Color(0xFF7C4DFF), // Royal Violet
        Color(0xFF536DFE), // Indigo
        Color(0xFF448AFF), // Electric Blue
        Color(0xFF18FFFF), // Neon Cyan
        Color(0xFF64FFDA), // Turquoise
        Color(0xFF69F0AE), // Neon Green
        Color(0xFFEEFF41), // Neon Yellow
        Color(0xFFFFD700), // Gold
        Color(0xFFFF6D00), // Tangerine
        Color(0xFFDD2C00), // Deep Rust
        Color(0xFF4E342E), // Chocolate
        Color(0xFF37474F), // Slate Dark
        Color(0xFFCFD8DC)  // Silver Gray
    )
}
