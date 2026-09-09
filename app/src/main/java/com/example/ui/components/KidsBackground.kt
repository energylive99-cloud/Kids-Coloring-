package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke

/**
 * Soft, elegant pastel atmosphere with subtle playful decorative elements
 * (tiny stars, gentle cloud puffs, sparkles, and pastel bubbles) for young children.
 */
@Composable
fun KidsPastelBackground(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFFFDF8), // Soft Cream
                        Color(0xFFFBF4FF), // Whisper Lavender-Pink
                        Color(0xFFF3FAFF), // Whisper Baby Blue
                        Color(0xFFF2FBF6), // Whisper Mint
                        Color(0xFFFFFDF5)  // Soft Warm Cream at bottom
                    )
                )
            )
    ) {
        // Subtle decorative background canvas (very low opacity so it never distracts)
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // 1. Soft atmospheric pastel glowing spots
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(Color(0xFFFFEE58).copy(alpha = 0.08f), Color.Transparent),
                    center = Offset(w * 0.15f, h * 0.12f),
                    radius = w * 0.45f
                )
            )
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(Color(0xFF80D8FF).copy(alpha = 0.08f), Color.Transparent),
                    center = Offset(w * 0.88f, h * 0.28f),
                    radius = w * 0.50f
                )
            )
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(Color(0xFFFF80AB).copy(alpha = 0.07f), Color.Transparent),
                    center = Offset(w * 0.10f, h * 0.65f),
                    radius = w * 0.45f
                )
            )
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(Color(0xFFB9F6CA).copy(alpha = 0.08f), Color.Transparent),
                    center = Offset(w * 0.85f, h * 0.80f),
                    radius = w * 0.45f
                )
            )

            // 2. Subtle decorative clouds
            fun drawSubtleCloud(x: Float, y: Float, cloudW: Float, color: Color) {
                val r = cloudW * 0.35f
                drawCircle(color = color, center = Offset(x, y), radius = r)
                drawCircle(color = color, center = Offset(x + r * 0.8f, y - r * 0.2f), radius = r * 0.85f)
                drawCircle(color = color, center = Offset(x - r * 0.8f, y + r * 0.1f), radius = r * 0.7f)
                drawCircle(color = color, center = Offset(x + r * 1.5f, y + r * 0.15f), radius = r * 0.65f)
            }
            drawSubtleCloud(w * 0.85f, h * 0.08f, w * 0.25f, Color(0xFFE2E8F0).copy(alpha = 0.18f))
            drawSubtleCloud(w * 0.12f, h * 0.42f, w * 0.20f, Color(0xFFE0E7FF).copy(alpha = 0.15f))
            drawSubtleCloud(w * 0.90f, h * 0.58f, w * 0.22f, Color(0xFFFCE7F3).copy(alpha = 0.16f))

            // 3. Subtle twinkling 4-point stars
            fun drawSubtleStar(center: Offset, starSize: Float, color: Color) {
                val path = Path().apply {
                    moveTo(center.x, center.y - starSize)
                    quadraticBezierTo(center.x, center.y, center.x + starSize, center.y)
                    quadraticBezierTo(center.x, center.y, center.x, center.y + starSize)
                    quadraticBezierTo(center.x, center.y, center.x - starSize, center.y)
                    quadraticBezierTo(center.x, center.y, center.x, center.y - starSize)
                    close()
                }
                drawPath(path, color = color)
            }

            val starPositions = listOf(
                Triple(Offset(w * 0.28f, h * 0.05f), w * 0.024f, Color(0xFFFFB300).copy(alpha = 0.25f)),
                Triple(Offset(w * 0.72f, h * 0.04f), w * 0.020f, Color(0xFF2979FF).copy(alpha = 0.20f)),
                Triple(Offset(w * 0.06f, h * 0.22f), w * 0.028f, Color(0xFFFF4081).copy(alpha = 0.22f)),
                Triple(Offset(w * 0.94f, h * 0.38f), w * 0.024f, Color(0xFFFFB300).copy(alpha = 0.25f)),
                Triple(Offset(w * 0.48f, h * 0.48f), w * 0.018f, Color(0xFF00E676).copy(alpha = 0.20f)),
                Triple(Offset(w * 0.05f, h * 0.78f), w * 0.025f, Color(0xFF2979FF).copy(alpha = 0.20f)),
                Triple(Offset(w * 0.92f, h * 0.72f), w * 0.022f, Color(0xFFFF4081).copy(alpha = 0.22f)),
                Triple(Offset(w * 0.35f, h * 0.92f), w * 0.024f, Color(0xFFFFB300).copy(alpha = 0.22f))
            )
            for ((pos, starSize, color) in starPositions) {
                drawSubtleStar(pos, starSize, color)
            }

            // 4. Subtle tiny sparkle dots & playful pastel bubbles with specular highlights
            val dots = listOf(
                Offset(w * 0.44f, h * 0.09f) to Color(0xFFFFD54F).copy(alpha = 0.35f),
                Offset(w * 0.80f, h * 0.18f) to Color(0xFF81D4FA).copy(alpha = 0.35f),
                Offset(w * 0.16f, h * 0.30f) to Color(0xFFF48FB1).copy(alpha = 0.35f),
                Offset(w * 0.84f, h * 0.48f) to Color(0xFFA5D6A7).copy(alpha = 0.35f),
                Offset(w * 0.22f, h * 0.85f) to Color(0xFFCE93D8).copy(alpha = 0.35f),
                Offset(w * 0.76f, h * 0.88f) to Color(0xFFFFCC80).copy(alpha = 0.35f)
            )
            for ((dotPos, dotColor) in dots) {
                drawCircle(color = dotColor, center = dotPos, radius = w * 0.008f)
            }

            // 5. Whimsical translucent pastel bubbles with specular shines
            fun drawPlayfulBubble(center: Offset, radius: Float, bubbleColor: Color) {
                // Outer subtle ring
                drawCircle(
                    color = bubbleColor.copy(alpha = 0.28f),
                    center = center,
                    radius = radius,
                    style = Stroke(width = radius * 0.12f)
                )
                // Soft gradient body
                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(Color.White.copy(alpha = 0.22f), bubbleColor.copy(alpha = 0.06f)),
                        center = center,
                        radius = radius
                    ),
                    center = center,
                    radius = radius
                )
                // Cute specular reflection dot
                drawCircle(
                    color = Color.White.copy(alpha = 0.75f),
                    center = Offset(center.x - radius * 0.35f, center.y - radius * 0.35f),
                    radius = radius * 0.22f
                )
            }

            drawPlayfulBubble(Offset(w * 0.10f, h * 0.14f), w * 0.038f, Color(0xFF81D4FA))
            drawPlayfulBubble(Offset(w * 0.90f, h * 0.20f), w * 0.045f, Color(0xFFFF80AB))
            drawPlayfulBubble(Offset(w * 0.06f, h * 0.52f), w * 0.042f, Color(0xFFB9F6CA))
            drawPlayfulBubble(Offset(w * 0.93f, h * 0.65f), w * 0.036f, Color(0xFFFFD54F))
            drawPlayfulBubble(Offset(w * 0.14f, h * 0.92f), w * 0.048f, Color(0xFFCE93D8))
        }

        // Render main content above the soft background
        content()
    }
}
