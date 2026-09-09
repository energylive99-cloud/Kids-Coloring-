package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// ====================================================================
// 1. ANIMALS: Adorable puppy with floppy ears, sparkling eyes & blush
// ====================================================================
@Composable
fun AnimalIllustration(
    modifier: Modifier = Modifier,
    size: Dp = 80.dp
) {
    Box(
        modifier = modifier.size(size),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = this.size.width
            val h = this.size.height

            // Soft shadow beneath puppy
            drawOval(
                color = Color(0x1A8D4E27),
                topLeft = Offset(w * 0.18f, h * 0.82f),
                size = Size(w * 0.64f, h * 0.14f)
            )

            // Floppy Ears (Behind head)
            // Left ear
            val leftEarPath = Path().apply {
                moveTo(w * 0.22f, h * 0.32f)
                cubicTo(w * 0.05f, h * 0.38f, w * 0.04f, h * 0.68f, w * 0.18f, h * 0.70f)
                cubicTo(w * 0.28f, h * 0.70f, w * 0.32f, h * 0.52f, w * 0.28f, h * 0.36f)
                close()
            }
            drawPath(leftEarPath, color = Color(0xFFD97706)) // Warm caramel brown
            drawPath(leftEarPath, color = Color(0xFFB45309), style = Stroke(width = w * 0.03f))

            // Right ear
            val rightEarPath = Path().apply {
                moveTo(w * 0.78f, h * 0.32f)
                cubicTo(w * 0.95f, h * 0.38f, w * 0.96f, h * 0.68f, w * 0.82f, h * 0.70f)
                cubicTo(w * 0.72f, h * 0.70f, w * 0.68f, h * 0.52f, w * 0.72f, h * 0.36f)
                close()
            }
            drawPath(rightEarPath, color = Color(0xFFD97706))
            drawPath(rightEarPath, color = Color(0xFFB45309), style = Stroke(width = w * 0.03f))

            // Head shape
            val headRect = Rect(w * 0.16f, h * 0.18f, w * 0.84f, h * 0.78f)
            drawOval(
                color = Color(0xFFFBBF24), // Golden puppy fur
                topLeft = Offset(headRect.left, headRect.top),
                size = Size(headRect.width, headRect.height)
            )
            drawOval(
                color = Color(0xFFD97706),
                topLeft = Offset(headRect.left, headRect.top),
                size = Size(headRect.width, headRect.height),
                style = Stroke(width = w * 0.032f)
            )

            // White eye patch over left eye
            drawOval(
                color = Color(0xFFFEF3C7),
                topLeft = Offset(w * 0.24f, h * 0.26f),
                size = Size(w * 0.24f, h * 0.26f)
            )

            // Big friendly cartoon eyes
            // Left eye
            val eyeRadius = w * 0.075f
            drawCircle(
                color = Color(0xFF1E293B),
                center = Offset(w * 0.36f, h * 0.42f),
                radius = eyeRadius
            )
            // Sparkles in left eye
            drawCircle(color = Color.White, center = Offset(w * 0.34f, h * 0.39f), radius = eyeRadius * 0.42f)
            drawCircle(color = Color.White, center = Offset(w * 0.38f, h * 0.45f), radius = eyeRadius * 0.22f)

            // Right eye
            drawCircle(
                color = Color(0xFF1E293B),
                center = Offset(w * 0.64f, h * 0.42f),
                radius = eyeRadius
            )
            // Sparkles in right eye
            drawCircle(color = Color.White, center = Offset(w * 0.62f, h * 0.39f), radius = eyeRadius * 0.42f)
            drawCircle(color = Color.White, center = Offset(w * 0.66f, h * 0.45f), radius = eyeRadius * 0.22f)

            // Sweet rosy blushing cheeks
            drawOval(
                color = Color(0xFFFF80AB).copy(alpha = 0.55f),
                topLeft = Offset(w * 0.20f, h * 0.50f),
                size = Size(w * 0.15f, h * 0.09f)
            )
            drawOval(
                color = Color(0xFFFF80AB).copy(alpha = 0.55f),
                topLeft = Offset(w * 0.65f, h * 0.50f),
                size = Size(w * 0.15f, h * 0.09f)
            )

            // Muzzle area (cream white)
            val muzzleRect = Rect(w * 0.34f, h * 0.48f, w * 0.66f, h * 0.74f)
            drawOval(
                color = Color(0xFFFFFBEB),
                topLeft = Offset(muzzleRect.left, muzzleRect.top),
                size = Size(muzzleRect.width, muzzleRect.height)
            )

            // Cute black puppy nose
            val nosePath = Path().apply {
                moveTo(w * 0.44f, h * 0.52f)
                lineTo(w * 0.56f, h * 0.52f)
                cubicTo(w * 0.56f, h * 0.58f, w * 0.52f, h * 0.61f, w * 0.50f, h * 0.61f)
                cubicTo(w * 0.48f, h * 0.61f, w * 0.44f, h * 0.58f, w * 0.44f, h * 0.52f)
                close()
            }
            drawPath(nosePath, color = Color(0xFF1E293B))
            // Nose shine
            drawCircle(color = Color.White.copy(alpha = 0.8f), center = Offset(w * 0.48f, h * 0.54f), radius = w * 0.015f)

            // Happy smile & tongue
            val smilePath = Path().apply {
                moveTo(w * 0.42f, h * 0.63f)
                quadraticBezierTo(w * 0.50f, h * 0.71f, w * 0.58f, h * 0.63f)
            }
            drawPath(
                smilePath,
                color = Color(0xFF451A03),
                style = Stroke(width = w * 0.03f, cap = StrokeCap.Round)
            )

            // Cute little tongue
            val tonguePath = Path().apply {
                moveTo(w * 0.46f, h * 0.66f)
                cubicTo(w * 0.46f, h * 0.74f, w * 0.54f, h * 0.74f, w * 0.54f, h * 0.66f)
                close()
            }
            drawPath(tonguePath, color = Color(0xFFFF5252))

            // Two cute paws peeking from bottom
            drawOval(
                color = Color(0xFFFFFBEB),
                topLeft = Offset(w * 0.24f, h * 0.74f),
                size = Size(w * 0.20f, h * 0.16f)
            )
            drawOval(
                color = Color(0xFFD97706),
                topLeft = Offset(w * 0.24f, h * 0.74f),
                size = Size(w * 0.20f, h * 0.16f),
                style = Stroke(width = w * 0.025f)
            )

            drawOval(
                color = Color(0xFFFFFBEB),
                topLeft = Offset(w * 0.56f, h * 0.74f),
                size = Size(w * 0.20f, h * 0.16f)
            )
            drawOval(
                color = Color(0xFFD97706),
                topLeft = Offset(w * 0.56f, h * 0.74f),
                size = Size(w * 0.20f, h * 0.16f),
                style = Stroke(width = w * 0.025f)
            )
        }
    }
}

// ====================================================================
// 2. VEHICLES: Cheerful red car with friendly eyes and headlights
// ====================================================================
@Composable
fun VehicleIllustration(
    modifier: Modifier = Modifier,
    size: Dp = 80.dp
) {
    Box(
        modifier = modifier.size(size),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = this.size.width
            val h = this.size.height

            // Soft shadow on the road
            drawOval(
                color = Color(0x1A0284C7),
                topLeft = Offset(w * 0.08f, h * 0.78f),
                size = Size(w * 0.84f, h * 0.16f)
            )

            // Cute exhaust cloud puff
            drawCircle(color = Color(0xFFE2E8F0), center = Offset(w * 0.08f, h * 0.66f), radius = w * 0.06f)
            drawCircle(color = Color.White, center = Offset(w * 0.07f, h * 0.65f), radius = w * 0.045f)

            // Car Cabin (Top dome)
            val cabinPath = Path().apply {
                moveTo(w * 0.26f, h * 0.48f)
                cubicTo(w * 0.30f, h * 0.22f, w * 0.68f, h * 0.22f, w * 0.74f, h * 0.48f)
                close()
            }
            drawPath(cabinPath, color = Color(0xFFEF4444))
            drawPath(cabinPath, color = Color(0xFFB91C1C), style = Stroke(width = w * 0.03f))

            // Windshield (Friendly blue with eye gaze)
            val windshieldPath = Path().apply {
                moveTo(w * 0.32f, h * 0.46f)
                cubicTo(w * 0.36f, h * 0.26f, w * 0.64f, h * 0.26f, w * 0.68f, h * 0.46f)
                close()
            }
            drawPath(windshieldPath, color = Color(0xFFBAE6FD))
            drawPath(windshieldPath, color = Color(0xFF38BDF8), style = Stroke(width = w * 0.02f))

            // Eyes on windshield
            drawCircle(color = Color(0xFF0F172A), center = Offset(w * 0.44f, h * 0.38f), radius = w * 0.042f)
            drawCircle(color = Color.White, center = Offset(w * 0.43f, h * 0.36f), radius = w * 0.02f)

            drawCircle(color = Color(0xFF0F172A), center = Offset(w * 0.56f, h * 0.38f), radius = w * 0.042f)
            drawCircle(color = Color.White, center = Offset(w * 0.55f, h * 0.36f), radius = w * 0.02f)

            // Main Car Body (Rounded rectangle)
            val bodyRect = RoundRect(
                rect = Rect(w * 0.12f, h * 0.44f, w * 0.88f, h * 0.70f),
                cornerRadius = CornerRadius(w * 0.12f, w * 0.12f)
            )
            val bodyPath = Path().apply { addRoundRect(bodyRect) }
            drawPath(
                bodyPath,
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFFF5252), Color(0xFFE53935), Color(0xFFC62828))
                )
            )
            drawPath(bodyPath, color = Color(0xFFB91C1C), style = Stroke(width = w * 0.032f))

            // Glossy body highlight stripe
            val shinePath = Path().apply {
                moveTo(w * 0.20f, h * 0.50f)
                lineTo(w * 0.80f, h * 0.50f)
            }
            drawPath(
                shinePath,
                color = Color.White.copy(alpha = 0.5f),
                style = Stroke(width = w * 0.03f, cap = StrokeCap.Round)
            )

            // Front smiling bumper
            val smileBumper = Path().apply {
                moveTo(w * 0.40f, h * 0.60f)
                quadraticBezierTo(w * 0.50f, h * 0.66f, w * 0.60f, h * 0.60f)
            }
            drawPath(
                smileBumper,
                color = Color(0xFF1E293B),
                style = Stroke(width = w * 0.03f, cap = StrokeCap.Round)
            )

            // Headlights (Friendly glowing yellow)
            drawCircle(color = Color(0xFFFEF08A), center = Offset(w * 0.82f, h * 0.56f), radius = w * 0.07f)
            drawCircle(color = Color(0xFFEAB308), center = Offset(w * 0.82f, h * 0.56f), radius = w * 0.07f, style = Stroke(width = w * 0.025f))

            // Wheels (Front & Back)
            // Left Wheel
            drawCircle(color = Color(0xFF1E293B), center = Offset(w * 0.28f, h * 0.70f), radius = w * 0.13f)
            drawCircle(color = Color(0xFF94A3B8), center = Offset(w * 0.28f, h * 0.70f), radius = w * 0.065f)
            drawCircle(color = Color(0xFFF1F5F9), center = Offset(w * 0.28f, h * 0.70f), radius = w * 0.03f)

            // Right Wheel
            drawCircle(color = Color(0xFF1E293B), center = Offset(w * 0.72f, h * 0.70f), radius = w * 0.13f)
            drawCircle(color = Color(0xFF94A3B8), center = Offset(w * 0.72f, h * 0.70f), radius = w * 0.065f)
            drawCircle(color = Color(0xFFF1F5F9), center = Offset(w * 0.72f, h * 0.70f), radius = w * 0.03f)
        }
    }
}

// ====================================================================
// 3. DINOSAURS: Adorable baby friendly dinosaur
// ====================================================================
@Composable
fun DinosaurIllustration(
    modifier: Modifier = Modifier,
    size: Dp = 80.dp
) {
    Box(
        modifier = modifier.size(size),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = this.size.width
            val h = this.size.height

            // Soft shadow
            drawOval(
                color = Color(0x1A15803D),
                topLeft = Offset(w * 0.15f, h * 0.82f),
                size = Size(w * 0.70f, h * 0.14f)
            )

            // Orange/Yellow dorsal back plates
            val platePoints = listOf(
                Offset(w * 0.22f, h * 0.36f) to w * 0.06f,
                Offset(w * 0.28f, h * 0.25f) to w * 0.07f,
                Offset(w * 0.38f, h * 0.17f) to w * 0.08f,
                Offset(w * 0.50f, h * 0.14f) to w * 0.07f
            )
            for ((center, rad) in platePoints) {
                drawCircle(color = Color(0xFFFB923C), center = center, radius = rad)
                drawCircle(color = Color(0xFFEA580C), center = center, radius = rad, style = Stroke(width = w * 0.02f))
            }

            // Dino Tail (curved up)
            val tailPath = Path().apply {
                moveTo(w * 0.16f, h * 0.65f)
                cubicTo(w * 0.02f, h * 0.60f, w * 0.04f, h * 0.44f, w * 0.12f, h * 0.42f)
                cubicTo(w * 0.16f, h * 0.42f, w * 0.18f, h * 0.52f, w * 0.24f, h * 0.60f)
                close()
            }
            drawPath(tailPath, color = Color(0xFF4ADE80))
            drawPath(tailPath, color = Color(0xFF16A34A), style = Stroke(width = w * 0.03f))

            // Dino Body & Head (Plump friendly baby bronto shape)
            val bodyPath = Path().apply {
                moveTo(w * 0.20f, h * 0.65f)
                // Back curve up to neck
                cubicTo(w * 0.22f, h * 0.45f, w * 0.36f, h * 0.30f, w * 0.46f, h * 0.22f)
                // Head top & snout
                cubicTo(w * 0.54f, h * 0.14f, w * 0.78f, h * 0.15f, w * 0.84f, h * 0.28f)
                // Snout rounded corner & jaw
                cubicTo(w * 0.88f, h * 0.40f, w * 0.72f, h * 0.46f, w * 0.62f, h * 0.45f)
                // Front neck down to chest
                cubicTo(w * 0.60f, h * 0.52f, w * 0.68f, h * 0.65f, w * 0.68f, h * 0.74f)
                // Bottom belly & feet line
                cubicTo(w * 0.60f, h * 0.82f, w * 0.28f, h * 0.82f, w * 0.20f, h * 0.65f)
                close()
            }
            drawPath(
                bodyPath,
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF86EFAC), Color(0xFF4ADE80), Color(0xFF22C55E))
                )
            )
            drawPath(bodyPath, color = Color(0xFF16A34A), style = Stroke(width = w * 0.032f))

            // Soft Yellow Belly Patch
            val bellyPath = Path().apply {
                moveTo(w * 0.42f, h * 0.44f)
                cubicTo(w * 0.58f, h * 0.46f, w * 0.65f, h * 0.60f, w * 0.64f, h * 0.74f)
                cubicTo(w * 0.55f, h * 0.78f, w * 0.42f, h * 0.76f, w * 0.36f, h * 0.70f)
                close()
            }
            drawPath(bellyPath, color = Color(0xFFFEF08A))

            // Belly decorative stripes
            drawLine(color = Color(0xFFFACC15), start = Offset(w * 0.42f, h * 0.58f), end = Offset(w * 0.58f, h * 0.58f), strokeWidth = w * 0.025f, cap = StrokeCap.Round)
            drawLine(color = Color(0xFFFACC15), start = Offset(w * 0.40f, h * 0.66f), end = Offset(w * 0.56f, h * 0.66f), strokeWidth = w * 0.025f, cap = StrokeCap.Round)

            // Little cute feet
            drawOval(color = Color(0xFF22C55E), topLeft = Offset(w * 0.28f, h * 0.74f), size = Size(w * 0.16f, h * 0.12f))
            drawOval(color = Color(0xFF16A34A), topLeft = Offset(w * 0.28f, h * 0.74f), size = Size(w * 0.16f, h * 0.12f), style = Stroke(width = w * 0.025f))

            drawOval(color = Color(0xFF22C55E), topLeft = Offset(w * 0.48f, h * 0.74f), size = Size(w * 0.16f, h * 0.12f))
            drawOval(color = Color(0xFF16A34A), topLeft = Offset(w * 0.48f, h * 0.74f), size = Size(w * 0.16f, h * 0.12f), style = Stroke(width = w * 0.025f))

            // Big friendly eye
            drawCircle(color = Color(0xFF0F172A), center = Offset(w * 0.66f, h * 0.26f), radius = w * 0.07f)
            drawCircle(color = Color.White, center = Offset(w * 0.64f, h * 0.24f), radius = w * 0.035f)
            drawCircle(color = Color.White, center = Offset(w * 0.68f, h * 0.29f), radius = w * 0.018f)

            // Cute nostril
            drawCircle(color = Color(0xFF16A34A), center = Offset(w * 0.82f, h * 0.28f), radius = w * 0.02f)

            // Rosy blushing cheek
            drawCircle(color = Color(0xFFFF80AB).copy(alpha = 0.65f), center = Offset(w * 0.64f, h * 0.35f), radius = w * 0.055f)

            // Happy smile
            val dinoSmile = Path().apply {
                moveTo(w * 0.72f, h * 0.38f)
                quadraticBezierTo(w * 0.78f, h * 0.44f, w * 0.84f, h * 0.36f)
            }
            drawPath(dinoSmile, color = Color(0xFF14532D), style = Stroke(width = w * 0.03f, cap = StrokeCap.Round))
        }
    }
}

// ====================================================================
// 4. FRUITS: Cheerful glossy red apple with smiling face & leaf
// ====================================================================
@Composable
fun FruitIllustration(
    modifier: Modifier = Modifier,
    size: Dp = 80.dp
) {
    Box(
        modifier = modifier.size(size),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = this.size.width
            val h = this.size.height

            // Soft shadow
            drawOval(
                color = Color(0x1ABE185D),
                topLeft = Offset(w * 0.18f, h * 0.80f),
                size = Size(w * 0.64f, h * 0.14f)
            )

            // Wooden brown stem
            val stemPath = Path().apply {
                moveTo(w * 0.48f, h * 0.28f)
                cubicTo(w * 0.46f, h * 0.12f, w * 0.54f, h * 0.06f, w * 0.60f, h * 0.06f)
                cubicTo(w * 0.57f, h * 0.14f, w * 0.54f, h * 0.24f, w * 0.52f, h * 0.28f)
                close()
            }
            drawPath(stemPath, color = Color(0xFF78350F))

            // Fresh green leaf
            val leafPath = Path().apply {
                moveTo(w * 0.52f, h * 0.16f)
                cubicTo(w * 0.68f, h * 0.06f, w * 0.86f, h * 0.10f, w * 0.88f, h * 0.18f)
                cubicTo(w * 0.82f, h * 0.28f, w * 0.64f, h * 0.26f, w * 0.52f, h * 0.16f)
                close()
            }
            drawPath(leafPath, color = Color(0xFF4ADE80))
            drawPath(leafPath, color = Color(0xFF16A34A), style = Stroke(width = w * 0.02f))
            // Leaf vein
            drawLine(color = Color(0xFF16A34A), start = Offset(w * 0.54f, h * 0.17f), end = Offset(w * 0.80f, h * 0.18f), strokeWidth = w * 0.015f)

            // Apple Body (Juicy heart/round plump shape)
            val applePath = Path().apply {
                moveTo(w * 0.50f, h * 0.28f)
                // Left shoulder & side
                cubicTo(w * 0.32f, h * 0.20f, w * 0.10f, h * 0.36f, w * 0.14f, h * 0.60f)
                // Bottom left to bottom dimple
                cubicTo(w * 0.16f, h * 0.80f, w * 0.40f, h * 0.84f, w * 0.50f, h * 0.78f)
                // Bottom right to side
                cubicTo(w * 0.60f, h * 0.84f, w * 0.84f, h * 0.80f, w * 0.86f, h * 0.60f)
                // Right side to top shoulder
                cubicTo(w * 0.90f, h * 0.36f, w * 0.68f, h * 0.20f, w * 0.50f, h * 0.28f)
                close()
            }
            drawPath(
                applePath,
                brush = Brush.radialGradient(
                    colors = listOf(Color(0xFFFF5252), Color(0xFFE11D48), Color(0xFFBE123C)),
                    center = Offset(w * 0.40f, h * 0.45f),
                    radius = w * 0.5f
                )
            )
            drawPath(applePath, color = Color(0xFF9F1239), style = Stroke(width = w * 0.032f))

            // Glossy shine reflection arc
            val shineArc = Path().apply {
                moveTo(w * 0.24f, h * 0.35f)
                cubicTo(w * 0.20f, h * 0.48f, w * 0.24f, h * 0.62f, w * 0.30f, h * 0.68f)
            }
            drawPath(
                shineArc,
                color = Color.White.copy(alpha = 0.65f),
                style = Stroke(width = w * 0.045f, cap = StrokeCap.Round)
            )

            // Sweet cartoon face
            // Left eye
            drawCircle(color = Color(0xFF1E293B), center = Offset(w * 0.38f, h * 0.50f), radius = w * 0.06f)
            drawCircle(color = Color.White, center = Offset(w * 0.36f, h * 0.48f), radius = w * 0.025f)

            // Right eye
            drawCircle(color = Color(0xFF1E293B), center = Offset(w * 0.62f, h * 0.50f), radius = w * 0.06f)
            drawCircle(color = Color.White, center = Offset(w * 0.60f, h * 0.48f), radius = w * 0.025f)

            // Rosy blushing cheeks
            drawCircle(color = Color(0xFFFF80AB), center = Offset(w * 0.28f, h * 0.56f), radius = w * 0.05f)
            drawCircle(color = Color(0xFFFF80AB), center = Offset(w * 0.72f, h * 0.56f), radius = w * 0.05f)

            // Happy smile
            val appleSmile = Path().apply {
                moveTo(w * 0.42f, h * 0.58f)
                quadraticBezierTo(w * 0.50f, h * 0.68f, w * 0.58f, h * 0.58f)
            }
            drawPath(appleSmile, color = Color(0xFF4C0519), style = Stroke(width = w * 0.03f, cap = StrokeCap.Round))
        }
    }
}

// ====================================================================
// 5. NATURE: Magical pastel rainbow with fluffy clouds & golden sun
// ====================================================================
@Composable
fun NatureIllustration(
    modifier: Modifier = Modifier,
    size: Dp = 80.dp
) {
    Box(
        modifier = modifier.size(size),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = this.size.width
            val h = this.size.height

            // Smiling golden sun behind rainbow
            val sunCenter = Offset(w * 0.50f, h * 0.32f)
            drawCircle(color = Color(0xFFFDE047), center = sunCenter, radius = w * 0.22f)
            drawCircle(color = Color(0xFFEAB308), center = sunCenter, radius = w * 0.22f, style = Stroke(width = w * 0.02f))

            // Sun smile & cute eyes
            drawCircle(color = Color(0xFF713F12), center = Offset(w * 0.45f, h * 0.28f), radius = w * 0.025f)
            drawCircle(color = Color(0xFF713F12), center = Offset(w * 0.55f, h * 0.28f), radius = w * 0.025f)
            val sunSmile = Path().apply {
                moveTo(w * 0.46f, h * 0.34f)
                quadraticBezierTo(w * 0.50f, h * 0.38f, w * 0.54f, h * 0.34f)
            }
            drawPath(sunSmile, color = Color(0xFF713F12), style = Stroke(width = w * 0.02f, cap = StrokeCap.Round))

            // Pastel Rainbow Arches
            val rainbowStripes = listOf(
                Color(0xFFFF5252) to w * 0.44f, // Joyful Coral Pink
                Color(0xFFFFB300) to w * 0.37f, // Joyful Sun Amber
                Color(0xFF00E676) to w * 0.30f, // Joyful Fresh Mint
                Color(0xFF2979FF) to w * 0.23f  // Joyful Sky Blue
            )
            for ((color, radius) in rainbowStripes) {
                drawCircle(
                    color = color,
                    center = Offset(w * 0.50f, h * 0.72f),
                    radius = radius,
                    style = Stroke(width = w * 0.07f)
                )
            }

            // Left Fluffy Cloud
            val leftCloud = Path().apply {
                addOval(Rect(w * 0.06f, h * 0.54f, w * 0.34f, h * 0.76f))
            }
            drawCircle(color = Color.White, center = Offset(w * 0.16f, h * 0.62f), radius = w * 0.10f)
            drawCircle(color = Color.White, center = Offset(w * 0.28f, h * 0.58f), radius = w * 0.12f)
            drawCircle(color = Color.White, center = Offset(w * 0.36f, h * 0.66f), radius = w * 0.09f)
            drawCircle(color = Color(0xFFCBD5E1), center = Offset(w * 0.28f, h * 0.58f), radius = w * 0.12f, style = Stroke(width = w * 0.02f))

            // Right Fluffy Cloud
            drawCircle(color = Color.White, center = Offset(w * 0.64f, h * 0.66f), radius = w * 0.09f)
            drawCircle(color = Color.White, center = Offset(w * 0.72f, h * 0.58f), radius = w * 0.12f)
            drawCircle(color = Color.White, center = Offset(w * 0.84f, h * 0.62f), radius = w * 0.10f)
            drawCircle(color = Color(0xFFCBD5E1), center = Offset(w * 0.72f, h * 0.58f), radius = w * 0.12f, style = Stroke(width = w * 0.02f))

            // Floating sparkles
            drawCircle(color = Color(0xFFFFD54F), center = Offset(w * 0.14f, h * 0.32f), radius = w * 0.035f)
            drawCircle(color = Color(0xFFFFD54F), center = Offset(w * 0.86f, h * 0.34f), radius = w * 0.035f)
        }
    }
}

// ====================================================================
// 6. SPACE: Cute rocket ship zooming with stars & ringed planet
// ====================================================================
@Composable
fun SpaceIllustration(
    modifier: Modifier = Modifier,
    size: Dp = 80.dp
) {
    Box(
        modifier = modifier.size(size),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = this.size.width
            val h = this.size.height

            // Small pastel ringed planet in background
            val planetCenter = Offset(w * 0.22f, h * 0.26f)
            drawCircle(color = Color(0xFFC084FC), center = planetCenter, radius = w * 0.10f)
            drawOval(
                color = Color(0xFFF0ABFC),
                topLeft = Offset(planetCenter.x - w * 0.16f, planetCenter.y - w * 0.04f),
                size = Size(w * 0.32f, w * 0.08f),
                style = Stroke(width = w * 0.025f)
            )

            // Twinkling yellow stars
            val stars = listOf(
                Offset(w * 0.15f, h * 0.60f) to w * 0.035f,
                Offset(w * 0.82f, h * 0.18f) to w * 0.045f,
                Offset(w * 0.88f, h * 0.65f) to w * 0.035f
            )
            for ((pos, r) in stars) {
                drawCircle(color = Color(0xFFFDE047), center = pos, radius = r)
                drawCircle(color = Color.White, center = pos, radius = r * 0.5f)
            }

            // Rocket Flame Exhaust (Warm orange & yellow)
            val flamePath = Path().apply {
                moveTo(w * 0.44f, h * 0.70f)
                cubicTo(w * 0.40f, h * 0.88f, w * 0.50f, h * 0.96f, w * 0.50f, h * 0.96f)
                cubicTo(w * 0.50f, h * 0.96f, w * 0.60f, h * 0.88f, w * 0.56f, h * 0.70f)
                close()
            }
            drawPath(flamePath, color = Color(0xFFFB923C))
            // Inner yellow flame
            val innerFlame = Path().apply {
                moveTo(w * 0.47f, h * 0.70f)
                cubicTo(w * 0.45f, h * 0.82f, w * 0.50f, h * 0.88f, w * 0.50f, h * 0.88f)
                cubicTo(w * 0.50f, h * 0.88f, w * 0.55f, h * 0.82f, w * 0.53f, h * 0.70f)
                close()
            }
            drawPath(innerFlame, color = Color(0xFFFEF08A))

            // Left Fin
            val leftFin = Path().apply {
                moveTo(w * 0.38f, h * 0.56f)
                cubicTo(w * 0.22f, h * 0.62f, w * 0.24f, h * 0.76f, w * 0.26f, h * 0.78f)
                lineTo(w * 0.38f, h * 0.70f)
                close()
            }
            drawPath(leftFin, color = Color(0xFFEF4444))
            drawPath(leftFin, color = Color(0xFFB91C1C), style = Stroke(width = w * 0.025f))

            // Right Fin
            val rightFin = Path().apply {
                moveTo(w * 0.62f, h * 0.56f)
                cubicTo(w * 0.78f, h * 0.62f, w * 0.76f, h * 0.76f, w * 0.74f, h * 0.78f)
                lineTo(w * 0.62f, h * 0.70f)
                close()
            }
            drawPath(rightFin, color = Color(0xFFEF4444))
            drawPath(rightFin, color = Color(0xFFB91C1C), style = Stroke(width = w * 0.025f))

            // Main Rocket Fuselage
            val rocketBody = Path().apply {
                moveTo(w * 0.50f, h * 0.12f)
                cubicTo(w * 0.66f, h * 0.30f, w * 0.66f, h * 0.58f, w * 0.62f, h * 0.72f)
                lineTo(w * 0.38f, h * 0.72f)
                cubicTo(w * 0.34f, h * 0.58f, w * 0.34f, h * 0.30f, w * 0.50f, h * 0.12f)
                close()
            }
            drawPath(rocketBody, color = Color.White)
            drawPath(rocketBody, color = Color(0xFF94A3B8), style = Stroke(width = w * 0.03f))

            // Rocket Nose Cone (Red)
            val noseCone = Path().apply {
                moveTo(w * 0.50f, h * 0.12f)
                cubicTo(w * 0.58f, h * 0.22f, w * 0.60f, h * 0.28f, w * 0.60f, h * 0.28f)
                lineTo(w * 0.40f, h * 0.28f)
                cubicTo(w * 0.40f, h * 0.28f, w * 0.42f, h * 0.22f, w * 0.50f, h * 0.12f)
                close()
            }
            drawPath(noseCone, color = Color(0xFFEF4444))
            drawPath(noseCone, color = Color(0xFFB91C1C), style = Stroke(width = w * 0.025f))

            // Circular Porthole window (Cyan with friendly frame)
            drawCircle(color = Color(0xFF0284C7), center = Offset(w * 0.50f, h * 0.44f), radius = w * 0.11f)
            drawCircle(color = Color(0xFFBAE6FD), center = Offset(w * 0.50f, h * 0.44f), radius = w * 0.085f)
            // Window shine
            drawCircle(color = Color.White, center = Offset(w * 0.48f, h * 0.41f), radius = w * 0.035f)
        }
    }
}

// ====================================================================
// 7. TOYS: Adorable cuddly teddy bear with ribbon bow
// ====================================================================
@Composable
fun ToysIllustration(
    modifier: Modifier = Modifier,
    size: Dp = 80.dp
) {
    Box(
        modifier = modifier.size(size),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = this.size.width
            val h = this.size.height

            // Soft shadow
            drawOval(
                color = Color(0x1AB45309),
                topLeft = Offset(w * 0.16f, h * 0.80f),
                size = Size(w * 0.68f, h * 0.15f)
            )

            // Teddy Ears
            // Left Ear
            drawCircle(color = Color(0xFFD97706), center = Offset(w * 0.26f, h * 0.28f), radius = w * 0.13f)
            drawCircle(color = Color(0xFFFDE68A), center = Offset(w * 0.26f, h * 0.28f), radius = w * 0.07f)
            drawCircle(color = Color(0xFFB45309), center = Offset(w * 0.26f, h * 0.28f), radius = w * 0.13f, style = Stroke(width = w * 0.025f))

            // Right Ear
            drawCircle(color = Color(0xFFD97706), center = Offset(w * 0.74f, h * 0.28f), radius = w * 0.13f)
            drawCircle(color = Color(0xFFFDE68A), center = Offset(w * 0.74f, h * 0.28f), radius = w * 0.07f)
            drawCircle(color = Color(0xFFB45309), center = Offset(w * 0.74f, h * 0.28f), radius = w * 0.13f, style = Stroke(width = w * 0.025f))

            // Teddy Head
            drawOval(
                color = Color(0xFFF59E0B),
                topLeft = Offset(w * 0.18f, h * 0.22f),
                size = Size(w * 0.64f, h * 0.54f)
            )
            drawOval(
                color = Color(0xFFB45309),
                topLeft = Offset(w * 0.18f, h * 0.22f),
                size = Size(w * 0.64f, h * 0.54f),
                style = Stroke(width = w * 0.03f)
            )

            // Sparkling cartoon eyes
            drawCircle(color = Color(0xFF1E293B), center = Offset(w * 0.36f, h * 0.42f), radius = w * 0.065f)
            drawCircle(color = Color.White, center = Offset(w * 0.34f, h * 0.40f), radius = w * 0.03f)

            drawCircle(color = Color(0xFF1E293B), center = Offset(w * 0.64f, h * 0.42f), radius = w * 0.065f)
            drawCircle(color = Color.White, center = Offset(w * 0.62f, h * 0.40f), radius = w * 0.03f)

            // Rosy blushing cheeks
            drawCircle(color = Color(0xFFFF80AB).copy(alpha = 0.6f), center = Offset(w * 0.26f, h * 0.50f), radius = w * 0.055f)
            drawCircle(color = Color(0xFFFF80AB).copy(alpha = 0.6f), center = Offset(w * 0.74f, h * 0.50f), radius = w * 0.055f)

            // Cream Muzzle
            drawOval(
                color = Color(0xFFFEF3C7),
                topLeft = Offset(w * 0.34f, h * 0.44f),
                size = Size(w * 0.32f, h * 0.24f)
            )

            // Dark embroidered nose
            val nosePath = Path().apply {
                moveTo(w * 0.44f, h * 0.48f)
                lineTo(w * 0.56f, h * 0.48f)
                cubicTo(w * 0.56f, h * 0.54f, w * 0.50f, h * 0.56f, w * 0.50f, h * 0.56f)
                cubicTo(w * 0.50f, h * 0.56f, w * 0.44f, h * 0.54f, w * 0.44f, h * 0.48f)
                close()
            }
            drawPath(nosePath, color = Color(0xFF451A03))

            // Happy smile line
            val bearSmile = Path().apply {
                moveTo(w * 0.43f, h * 0.58f)
                quadraticBezierTo(w * 0.50f, h * 0.64f, w * 0.57f, h * 0.58f)
            }
            drawPath(bearSmile, color = Color(0xFF451A03), style = Stroke(width = w * 0.025f, cap = StrokeCap.Round))

            // Red Ribbon Bow tie
            val bowLeft = Path().apply {
                moveTo(w * 0.50f, h * 0.74f)
                lineTo(w * 0.32f, h * 0.68f)
                lineTo(w * 0.32f, h * 0.80f)
                close()
            }
            drawPath(bowLeft, color = Color(0xFFEF4444))

            val bowRight = Path().apply {
                moveTo(w * 0.50f, h * 0.74f)
                lineTo(w * 0.68f, h * 0.68f)
                lineTo(w * 0.68f, h * 0.80f)
                close()
            }
            drawPath(bowRight, color = Color(0xFFEF4444))

            // Center bow knot
            drawCircle(color = Color(0xFFDC2626), center = Offset(w * 0.50f, h * 0.74f), radius = w * 0.05f)
        }
    }
}

// ====================================================================
// 8. ALPHABET: Colorful 3D ABC building blocks with sparkles
// ====================================================================
@Composable
fun AlphabetIllustration(
    modifier: Modifier = Modifier,
    size: Dp = 80.dp
) {
    Box(
        modifier = modifier.size(size),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = this.size.width
            val h = this.size.height

            // Soft shadow
            drawOval(
                color = Color(0x1A0284C7),
                topLeft = Offset(w * 0.10f, h * 0.80f),
                size = Size(w * 0.80f, h * 0.15f)
            )

            // --- BLOCK 'A' (Bottom Left - Coral Red) ---
            val aRect = RoundRect(
                rect = Rect(w * 0.12f, h * 0.44f, w * 0.48f, h * 0.80f),
                cornerRadius = CornerRadius(w * 0.06f, w * 0.06f)
            )
            val aPath = Path().apply { addRoundRect(aRect) }
            drawPath(aPath, color = Color(0xFFFF5252))
            drawPath(aPath, color = Color(0xFFB91C1C), style = Stroke(width = w * 0.025f))

            // Draw letter 'A'
            val letterAPath = Path().apply {
                moveTo(w * 0.23f, h * 0.72f)
                lineTo(w * 0.30f, h * 0.52f)
                lineTo(w * 0.37f, h * 0.72f)
            }
            drawPath(letterAPath, color = Color.White, style = Stroke(width = w * 0.045f, cap = StrokeCap.Round, join = StrokeJoin.Round))
            drawLine(color = Color.White, start = Offset(w * 0.25f, h * 0.65f), end = Offset(w * 0.35f, h * 0.65f), strokeWidth = w * 0.04f, cap = StrokeCap.Round)

            // --- BLOCK 'B' (Bottom Right - Sky Blue) ---
            val bRect = RoundRect(
                rect = Rect(w * 0.50f, h * 0.44f, w * 0.86f, h * 0.80f),
                cornerRadius = CornerRadius(w * 0.06f, w * 0.06f)
            )
            val bPath = Path().apply { addRoundRect(bRect) }
            drawPath(bPath, color = Color(0xFF0288D1))
            drawPath(bPath, color = Color(0xFF01579B), style = Stroke(width = w * 0.025f))

            // Draw letter 'B'
            val spineB = Path().apply {
                moveTo(w * 0.62f, h * 0.52f)
                lineTo(w * 0.62f, h * 0.72f)
            }
            drawPath(spineB, color = Color.White, style = Stroke(width = w * 0.045f, cap = StrokeCap.Round))
            val loopsB = Path().apply {
                moveTo(w * 0.62f, h * 0.52f)
                cubicTo(w * 0.76f, h * 0.52f, w * 0.76f, h * 0.62f, w * 0.62f, h * 0.62f)
                cubicTo(w * 0.78f, h * 0.62f, w * 0.78f, h * 0.72f, w * 0.62f, h * 0.72f)
            }
            drawPath(loopsB, color = Color.White, style = Stroke(width = w * 0.04f, cap = StrokeCap.Round))

            // --- BLOCK 'C' (Top Center - Amber / Golden Yellow) ---
            val cRect = RoundRect(
                rect = Rect(w * 0.30f, h * 0.12f, w * 0.68f, h * 0.48f),
                cornerRadius = CornerRadius(w * 0.06f, w * 0.06f)
            )
            val cPath = Path().apply { addRoundRect(cRect) }
            drawPath(cPath, color = Color(0xFFFFB300))
            drawPath(cPath, color = Color(0xFFD97706), style = Stroke(width = w * 0.025f))

            // Draw letter 'C'
            val letterCPath = Path().apply {
                moveTo(w * 0.56f, h * 0.22f)
                cubicTo(w * 0.40f, h * 0.20f, w * 0.40f, h * 0.40f, w * 0.56f, h * 0.38f)
            }
            drawPath(letterCPath, color = Color.White, style = Stroke(width = w * 0.048f, cap = StrokeCap.Round))

            // Sparkles around blocks
            drawCircle(color = Color(0xFFFFD54F), center = Offset(w * 0.16f, h * 0.24f), radius = w * 0.04f)
            drawCircle(color = Color(0xFFFFD54F), center = Offset(w * 0.84f, h * 0.32f), radius = w * 0.035f)
        }
    }
}

// ====================================================================
// 9. PALETTE LOGO ILLUSTRATION (For Header)
// ====================================================================
@Composable
fun PaletteLogoIllustration(
    modifier: Modifier = Modifier,
    size: Dp = 56.dp
) {
    Box(
        modifier = modifier.size(size),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = this.size.width
            val h = this.size.height

            // Wooden artist palette
            val palettePath = Path().apply {
                moveTo(w * 0.25f, h * 0.20f)
                cubicTo(w * 0.70f, h * 0.08f, w * 0.94f, h * 0.40f, w * 0.85f, h * 0.70f)
                cubicTo(w * 0.80f, h * 0.88f, w * 0.55f, h * 0.92f, w * 0.35f, h * 0.82f)
                // Thumb indentation
                cubicTo(w * 0.24f, h * 0.75f, w * 0.22f, h * 0.60f, w * 0.16f, h * 0.55f)
                cubicTo(w * 0.08f, h * 0.48f, w * 0.12f, h * 0.26f, w * 0.25f, h * 0.20f)
                close()
            }
            drawPath(
                palettePath,
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFFFEF3C7), Color(0xFFFDE68A), Color(0xFFF59E0B))
                )
            )
            drawPath(palettePath, color = Color(0xFFD97706), style = Stroke(width = w * 0.03f))

            // Thumb Hole
            drawCircle(color = Color(0xFFFFFDF8), center = Offset(w * 0.28f, h * 0.68f), radius = w * 0.08f)
            drawCircle(color = Color(0xFFD97706), center = Offset(w * 0.28f, h * 0.68f), radius = w * 0.08f, style = Stroke(width = w * 0.025f))

            // Vibrant paint dollops around the rim
            val dollops = listOf(
                Offset(w * 0.34f, h * 0.26f) to Color(0xFFFF5252), // Coral Red
                Offset(w * 0.55f, h * 0.22f) to Color(0xFFFFB300), // Amber Yellow
                Offset(w * 0.74f, h * 0.34f) to Color(0xFF00E676), // Mint Green
                Offset(w * 0.78f, h * 0.54f) to Color(0xFF2979FF), // Sky Blue
                Offset(w * 0.64f, h * 0.76f) to Color(0xFFB388FF)  // Lavender Purple
            )
            for ((center, color) in dollops) {
                drawCircle(color = color, center = center, radius = w * 0.075f)
                drawCircle(color = Color.White.copy(alpha = 0.6f), center = Offset(center.x - w * 0.02f, center.y - w * 0.02f), radius = w * 0.025f)
            }

            // Artist paintbrush crossing over
            val brushPath = Path().apply {
                moveTo(w * 0.88f, h * 0.12f)
                lineTo(w * 0.44f, h * 0.56f)
            }
            drawPath(
                brushPath,
                color = Color(0xFF78350F),
                style = Stroke(width = w * 0.05f, cap = StrokeCap.Round)
            )
            // Silver ferrule
            drawLine(color = Color(0xFFCBD5E1), start = Offset(w * 0.48f, h * 0.52f), end = Offset(w * 0.42f, h * 0.58f), strokeWidth = w * 0.06f, cap = StrokeCap.Round)
            // Coral paint bristle tip
            drawCircle(color = Color(0xFFFF5252), center = Offset(w * 0.38f, h * 0.62f), radius = w * 0.05f)
        }
    }
}

// ====================================================================
// 10. SAVED ART EASEL ILLUSTRATION (For "My Saved Drawings" feature card)
// ====================================================================
@Composable
fun SavedArtEaselIllustration(
    modifier: Modifier = Modifier,
    size: Dp = 68.dp
) {
    Box(
        modifier = modifier.size(size),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = this.size.width
            val h = this.size.height

            // Wooden Easel Legs (Tripod)
            // Left leg
            drawLine(color = Color(0xFF92400E), start = Offset(w * 0.50f, h * 0.10f), end = Offset(w * 0.16f, h * 0.90f), strokeWidth = w * 0.045f, cap = StrokeCap.Round)
            // Right leg
            drawLine(color = Color(0xFF92400E), start = Offset(w * 0.50f, h * 0.10f), end = Offset(w * 0.84f, h * 0.90f), strokeWidth = w * 0.045f, cap = StrokeCap.Round)
            // Center mast
            drawLine(color = Color(0xFFB45309), start = Offset(w * 0.50f, h * 0.06f), end = Offset(w * 0.50f, h * 0.88f), strokeWidth = w * 0.04f, cap = StrokeCap.Round)
            // Crossbar
            drawLine(color = Color(0xFF78350F), start = Offset(w * 0.14f, h * 0.68f), end = Offset(w * 0.86f, h * 0.68f), strokeWidth = w * 0.055f, cap = StrokeCap.Round)

            // Canvas Board (White with subtle drop shadow)
            val canvasRect = RoundRect(
                rect = Rect(w * 0.20f, h * 0.22f, w * 0.80f, h * 0.66f),
                cornerRadius = CornerRadius(w * 0.05f, w * 0.05f)
            )
            val canvasPath = Path().apply { addRoundRect(canvasRect) }
            drawPath(canvasPath, color = Color.White)
            drawPath(canvasPath, color = Color(0xFFE2E8F0), style = Stroke(width = w * 0.025f))

            // Painted Artwork on Canvas: Golden Star with Rainbow swoosh
            val starCenter = Offset(w * 0.50f, h * 0.44f)
            val starPath = Path().apply {
                val rOuter = w * 0.14f
                val rInner = w * 0.06f
                for (i in 0 until 5) {
                    val angleOuter = (i * 72 - 90) * (Math.PI / 180.0)
                    val xOuter = (starCenter.x + rOuter * Math.cos(angleOuter)).toFloat()
                    val yOuter = (starCenter.y + rOuter * Math.sin(angleOuter)).toFloat()
                    if (i == 0) moveTo(xOuter, yOuter) else lineTo(xOuter, yOuter)

                    val angleInner = ((i * 72 + 36) - 90) * (Math.PI / 180.0)
                    val xInner = (starCenter.x + rInner * Math.cos(angleInner)).toFloat()
                    val yInner = (starCenter.y + rInner * Math.sin(angleInner)).toFloat()
                    lineTo(xInner, yInner)
                }
                close()
            }
            drawPath(starPath, color = Color(0xFFFFB300))
            drawPath(starPath, color = Color(0xFFF59E0B), style = Stroke(width = w * 0.02f))

            // Cheerful color dots on canvas
            drawCircle(color = Color(0xFFFF5252), center = Offset(w * 0.32f, h * 0.34f), radius = w * 0.035f)
            drawCircle(color = Color(0xFF2979FF), center = Offset(w * 0.68f, h * 0.36f), radius = w * 0.035f)
            drawCircle(color = Color(0xFF00E676), center = Offset(w * 0.34f, h * 0.56f), radius = w * 0.03f)

            // Sparkle twinkles in the air
            drawCircle(color = Color(0xFFFFD54F), center = Offset(w * 0.88f, h * 0.22f), radius = w * 0.04f)
            drawCircle(color = Color(0xFFFFD54F), center = Offset(w * 0.12f, h * 0.38f), radius = w * 0.03f)
        }
    }
}
