package com.example.ui.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.Category
import com.example.ui.theme.CategoryThemeColors

/**
 * Premium illustrated category card designed specifically for children ages 3-8.
 * Features large custom vector artwork, soft pastel themed styling, high-contrast
 * readable typography, tactile bounce animation on tap, and clear visual hierarchy.
 */
@Composable
fun KidsCategoryCard(
    category: Category,
    pageCount: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val style = when (category.id.lowercase()) {
        "animals" -> CategoryThemeColors.Animals
        "vehicles" -> CategoryThemeColors.Vehicles
        "dinosaurs" -> CategoryThemeColors.Dinosaurs
        "fruits" -> CategoryThemeColors.Fruits
        "nature" -> CategoryThemeColors.Nature
        "space" -> CategoryThemeColors.Space
        "toys" -> CategoryThemeColors.Toys
        "alphabet" -> CategoryThemeColors.Alphabet
        else -> CategoryThemeColors.Animals
    }

    val emoji = when (category.id.lowercase()) {
        "animals" -> "🐾"
        "vehicles" -> "🚗"
        "dinosaurs" -> "🦖"
        "fruits" -> "🍓"
        "nature" -> "🌻"
        "space" -> "🚀"
        "toys" -> "🧸"
        "alphabet" -> "🔤"
        else -> "🎨"
    }

    val subtitle = when (category.id.lowercase()) {
        "animals" -> "Cute pets & pals"
        "vehicles" -> "Vroom & zoom!"
        "dinosaurs" -> "Friendly roars!"
        "fruits" -> "Sweet & juicy!"
        "nature" -> "Sun & rainbows!"
        "space" -> "Zoom to stars!"
        "toys" -> "Cuddly & fun!"
        "alphabet" -> "Learn ABCs!"
        else -> "Tap to explore"
    }

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    // Tactile bouncy feedback on press
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.94f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "category_card_scale_${category.id}"
    )

    Card(
        shape = RoundedCornerShape(26.dp),
        colors = CardDefaults.cardColors(containerColor = style.bg),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp, pressedElevation = 1.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(212.dp)
            .scale(scale)
            .clip(RoundedCornerShape(26.dp))
            .border(
                width = 1.5.dp,
                brush = Brush.verticalGradient(
                    colors = listOf(style.border, style.border.copy(alpha = 0.5f))
                ),
                shape = RoundedCornerShape(26.dp)
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
            .testTag("category_card_${category.id}")
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            style.bg,
                            Color.White.copy(alpha = 0.90f)
                        )
                    )
                )
                .padding(12.dp)
        ) {
            // Subtle backdrop soft glow circle for illustration pop
            Box(
                modifier = Modifier
                    .size(104.dp)
                    .align(Alignment.Center)
                    .clip(CircleShape)
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(Color.White, Color.White.copy(alpha = 0.70f))
                        )
                    )
            )

            // Top Row: Category Theme Badge on Left, Page Count Pill on Right
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopStart),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Category Theme Mini-Badge
                Surface(
                    shape = CircleShape,
                    color = Color.White.copy(alpha = 0.85f),
                    border = androidx.compose.foundation.BorderStroke(1.dp, style.border.copy(alpha = 0.7f)),
                    shadowElevation = 0.dp,
                    modifier = Modifier.size(28.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = emoji,
                            fontSize = 14.sp
                        )
                    }
                }

                // Page count pill
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = style.pillBg,
                    border = androidx.compose.foundation.BorderStroke(1.dp, style.border.copy(alpha = 0.7f)),
                    shadowElevation = 0.dp
                ) {
                    Text(
                        text = "$pageCount Pages",
                        fontSize = 10.5.sp,
                        fontWeight = FontWeight.Bold,
                        color = style.pillText,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }
            }

            // Center: Large Custom Vector Children's Illustration
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                contentAlignment = Alignment.Center
            ) {
                when (category.id.lowercase()) {
                    "animals" -> AnimalIllustration(size = 88.dp)
                    "vehicles" -> VehicleIllustration(size = 88.dp)
                    "dinosaurs" -> DinosaurIllustration(size = 88.dp)
                    "fruits" -> FruitIllustration(size = 88.dp)
                    "nature" -> NatureIllustration(size = 88.dp)
                    "space" -> SpaceIllustration(size = 88.dp)
                    "toys" -> ToysIllustration(size = 88.dp)
                    "alphabet" -> AlphabetIllustration(size = 88.dp)
                    else -> AnimalIllustration(size = 88.dp)
                }
            }

            // Bottom Area: Category Title, Subtitle, and Tactile Arrow
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = category.title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Black,
                        color = Color(0xFF1E293B),
                        lineHeight = 22.sp
                    )
                    Text(
                        text = subtitle,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF64748B),
                        lineHeight = 14.sp
                    )
                }

                // Tactile round arrow indicator with category accent color
                Surface(
                    shape = CircleShape,
                    color = style.accent,
                    shadowElevation = 2.dp,
                    modifier = Modifier.size(30.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Explore ${category.title}",
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }
    }
}
