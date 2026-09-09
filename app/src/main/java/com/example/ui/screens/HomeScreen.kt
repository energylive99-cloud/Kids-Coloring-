package com.example.ui.screens

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Collections
import androidx.compose.material.icons.filled.Settings
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ads.KidSafeBannerAd
import com.example.data.ColoringCatalog
import com.example.ui.components.KidsCategoryCard
import com.example.ui.components.KidsPastelBackground
import com.example.ui.components.PaletteLogoIllustration
import com.example.ui.components.SavedArtEaselIllustration
import com.example.ui.viewmodel.SavedDrawingsViewModel
import com.example.utils.rememberSoundManager

/**
 * Premium Kids Coloring World Home Screen for children ages 3–8.
 * Features a soft magical pastel atmosphere, polished rounded header with live saved drawings counter,
 * dedicated high-engagement 'My Saved Drawings' feature card, and a visually distinct 2-column
 * grid of high-quality illustrated category cards.
 */
@Composable
fun HomeScreen(
    onCategoryClick: (String) -> Unit,
    onSavedDrawingsClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    val context = LocalContext.current
    val categories = ColoringCatalog.categories
    val soundManager = rememberSoundManager()

    // Observe saved drawings count dynamically for the feature card and header badge
    val savedDrawingsViewModel: SavedDrawingsViewModel = viewModel(
        factory = SavedDrawingsViewModel.provideFactory(context)
    )
    val savedDrawings by savedDrawingsViewModel.savedDrawings.collectAsStateWithLifecycle()
    val savedCount = savedDrawings.size

    KidsPastelBackground(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .testTag("home_screen")
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 32.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .widthIn(max = 680.dp)
            ) {
                // 1. Polished Header Area with Logo, Studio Badge, and Quick Action Buttons
                item(span = { GridItemSpan(2) }) {
                    HomeHeaderArea(
                        savedCount = savedCount,
                        onSavedDrawingsClick = {
                            soundManager.playButtonClick()
                            onSavedDrawingsClick()
                        },
                        onSettingsClick = {
                            soundManager.playButtonClick()
                            onSettingsClick()
                        }
                    )
                }

                // 2. Dedicated 'My Saved Drawings' Feature Card
                item(span = { GridItemSpan(2) }) {
                    SavedDrawingsFeatureCard(
                        savedCount = savedCount,
                        onClick = {
                            soundManager.playButtonClick()
                            onSavedDrawingsClick()
                        }
                    )
                }

                // 3. Cheerful Section Header introducing the categories
                item(span = { GridItemSpan(2) }) {
                    SectionHeaderArea()
                }

                // 4. Illustrated Category Cards (Two-Column Grid Format)
                items(categories, key = { it.id }) { category ->
                    val pageCount = ColoringCatalog.getPagesForCategory(category.id).size
                    KidsCategoryCard(
                        category = category,
                        pageCount = pageCount,
                        onClick = {
                            soundManager.playCategoryClick()
                            onCategoryClick(category.id)
                        }
                    )
                }

                // 5. Child-Safe Ad Space with comfortable breathing room
                item(span = { GridItemSpan(2) }) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp, bottom = 12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        KidSafeBannerAd()
                    }
                }
            }
        }
    }
}

/**
 * Polished rounded header featuring artist paint palette logo, friendly greeting,
 * little artist studio chip, and tactile bounce action buttons with live badge indicator.
 */
@Composable
private fun HomeHeaderArea(
    savedCount: Int,
    onSavedDrawingsClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(28.dp),
        color = Color.White.copy(alpha = 0.90f),
        border = BorderStroke(1.dp, Color(0xFFE2E8F0)),
        shadowElevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // App Branding & Title
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                // Logo Container with soft warm frame
                Surface(
                    shape = RoundedCornerShape(18.dp),
                    color = Color(0xFFFEF3C7),
                    border = BorderStroke(1.5.dp, Color(0xFFFDE68A)),
                    shadowElevation = 1.dp,
                    modifier = Modifier.size(52.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        PaletteLogoIllustration(size = 44.dp)
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = "Kids Coloring",
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Black,
                        color = Color(0xFF1E293B),
                        letterSpacing = (-0.5).sp
                    )
                    Text(
                        text = "Paint a world of colors!",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF64748B)
                    )
                    // Little Artist Studio chip
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Color(0xFFFFF1F2),
                        border = BorderStroke(1.dp, Color(0xFFFFCCD5)),
                        modifier = Modifier.padding(top = 2.dp)
                    ) {
                        Text(
                            text = "✨ Little Artist Studio",
                            fontSize = 9.5.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFE11D48),
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 1.5.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Tactile Action Buttons
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                // My Drawings Button with dynamic badge
                HeaderIconButton(
                    icon = Icons.Default.Collections,
                    contentDescription = "My Drawings",
                    containerColor = Color(0xFFE0F2FE),
                    borderColor = Color(0xFFBAE6FD),
                    iconTint = Color(0xFF0284C7),
                    badgeCount = savedCount,
                    testTag = "gallery_button",
                    onClick = onSavedDrawingsClick
                )

                // Settings Button
                HeaderIconButton(
                    icon = Icons.Default.Settings,
                    contentDescription = "Settings",
                    containerColor = Color(0xFFFCE7F3),
                    borderColor = Color(0xFFFBCFE8),
                    iconTint = Color(0xFFE11D48),
                    badgeCount = 0,
                    testTag = "settings_button",
                    onClick = onSettingsClick
                )
            }
        }
    }
}

/**
 * Tactile bouncing header action button with minimum 48dp touch target and optional badge.
 */
@Composable
private fun HeaderIconButton(
    icon: ImageVector,
    contentDescription: String,
    containerColor: Color,
    borderColor: Color,
    iconTint: Color,
    badgeCount: Int = 0,
    testTag: String,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.90f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessMedium),
        label = "header_btn_scale"
    )

    Box {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = containerColor,
            border = BorderStroke(1.5.dp, borderColor),
            shadowElevation = 1.dp,
            modifier = Modifier
                .size(48.dp)
                .scale(scale)
                .clip(RoundedCornerShape(16.dp))
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = onClick
                )
                .testTag(testTag)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = icon,
                    contentDescription = contentDescription,
                    tint = iconTint,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        // Live badge bubble if count > 0
        if (badgeCount > 0) {
            Surface(
                shape = CircleShape,
                color = Color(0xFFFF5252),
                border = BorderStroke(1.5.dp, Color.White),
                shadowElevation = 2.dp,
                modifier = Modifier
                    .size(18.dp)
                    .align(Alignment.TopEnd)
                    .offset(x = 4.dp, y = (-4).dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = if (badgeCount > 99) "99+" else "$badgeCount",
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Black,
                        color = Color.White
                    )
                }
            }
        }
    }
}

/**
 * Dedicated 'My Saved Drawings' feature card with warm sunset gradient,
 * artist easel illustration, live masterpieces counter badge, colorful paint swatches,
 * and high-contrast tactile CTA button.
 */
@Composable
private fun SavedDrawingsFeatureCard(
    savedCount: Int,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.96f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessMedium),
        label = "saved_card_scale"
    )

    Card(
        shape = RoundedCornerShape(28.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp, pressedElevation = 1.dp),
        modifier = Modifier
            .fillMaxWidth()
            .scale(scale)
            .clip(RoundedCornerShape(28.dp))
            .border(
                width = 1.5.dp,
                brush = Brush.horizontalGradient(
                    colors = listOf(Color(0xFFFED7AA), Color(0xFFFBCFE8), Color(0xFFDDD6FE))
                ),
                shape = RoundedCornerShape(28.dp)
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
            .testTag("my_drawings_banner")
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFFFFF7ED), // Soft Peach Cream
                            Color(0xFFFEF3C7), // Warm Buttercream
                            Color(0xFFFDF2F8)  // Soft Rose
                        )
                    )
                )
                .padding(horizontal = 16.dp, vertical = 14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Artwork & Dynamic Title Info
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    SavedArtEaselIllustration(size = 68.dp)
                    Spacer(modifier = Modifier.width(14.dp))
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "My Saved Drawings",
                                fontSize = 17.5.sp,
                                fontWeight = FontWeight.Black,
                                color = Color(0xFF431407)
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(text = "⭐", fontSize = 14.sp)
                        }

                        Spacer(modifier = Modifier.height(3.dp))

                        // Dynamic Status Badge
                        if (savedCount > 0) {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = Color(0xFFFEF08A),
                                border = BorderStroke(1.dp, Color(0xFFFACC15))
                            ) {
                                Text(
                                    text = "$savedCount Masterpiece${if (savedCount == 1) "" else "s"} Saved! 🎉",
                                    fontSize = 10.5.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color(0xFF854D0E),
                                    modifier = Modifier.padding(horizontal = 7.dp, vertical = 2.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(3.dp))
                            Text(
                                text = "Tap to view, share & create!",
                                fontSize = 11.5.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF78350F)
                            )
                        } else {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = Color(0xFFFFEDD5),
                                border = BorderStroke(1.dp, Color(0xFFFED7AA))
                            ) {
                                Text(
                                    text = "Your Art Gallery 🎨",
                                    fontSize = 10.5.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color(0xFF9A3412),
                                    modifier = Modifier.padding(horizontal = 7.dp, vertical = 2.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(3.dp))
                            Text(
                                text = "Color pictures and save them here!",
                                fontSize = 11.5.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF78350F)
                            )
                        }

                        Spacer(modifier = Modifier.height(4.dp))

                        // Mini decorative paint dollops
                        Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                            Box(modifier = Modifier.size(7.dp).clip(CircleShape).background(Color(0xFFFF5252)))
                            Box(modifier = Modifier.size(7.dp).clip(CircleShape).background(Color(0xFFFFB300)))
                            Box(modifier = Modifier.size(7.dp).clip(CircleShape).background(Color(0xFF00E676)))
                            Box(modifier = Modifier.size(7.dp).clip(CircleShape).background(Color(0xFF2979FF)))
                            Box(modifier = Modifier.size(7.dp).clip(CircleShape).background(Color(0xFFB388FF)))
                        }
                    }
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Tactile CTA Button "Gallery"
                Surface(
                    shape = RoundedCornerShape(18.dp),
                    color = Color.Transparent,
                    shadowElevation = 2.dp,
                    modifier = Modifier.clip(RoundedCornerShape(18.dp))
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(Color(0xFFFF5722), Color(0xFFFF9800))
                                )
                            )
                            .padding(horizontal = 16.dp, vertical = 11.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = if (savedCount > 0) "Gallery" else "Open",
                                color = Color.White,
                                fontWeight = FontWeight.Black,
                                fontSize = 14.sp
                            )
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

/**
 * Cheerful section header introducing the coloring categories.
 */
@Composable
private fun SectionHeaderArea() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp, bottom = 2.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Coloring Worlds",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                    color = Color(0xFF1E293B)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = "🎨", fontSize = 18.sp)
            }
            Text(
                text = "Pick a fun theme to color & explore!",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF64748B)
            )
        }
    }
}

