package com.example.ads

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Isolated ads architecture ready for Google AdMob or family-safe mediation.
 */
object AdsConfig {
    /**
     * Easily toggled on/off during development or for ad-free experience.
     */
    var isAdsEnabled: Boolean = true

    // Standard Google AdMob Test IDs (safe for development)
    const val TEST_BANNER_AD_ID = "ca-app-pub-3940256099942544/6300978111"
    const val TEST_INTERSTITIAL_AD_ID = "ca-app-pub-3940256099942544/1033173712"
    const val TEST_REWARDED_AD_ID = "ca-app-pub-3940256099942544/5224354917"
}

interface AdsManager {
    fun showInterstitial(onDismiss: () -> Unit)
    fun isInterstitialAvailable(): Boolean
}

class DefaultAdsManager : AdsManager {
    private var actionCount = 0

    override fun showInterstitial(onDismiss: () -> Unit) {
        if (!AdsConfig.isAdsEnabled) {
            onDismiss()
            return
        }
        actionCount++
        // In full AdMob, loads and shows Google AdMob interstitial
        // For development MVP, safely calls onDismiss
        onDismiss()
    }

    override fun isInterstitialAvailable(): Boolean = AdsConfig.isAdsEnabled
}

/**
 * Child-safe test banner container that appears only on non-coloring screens (Home / Categories).
 * It clearly indicates "Test Ad" without being deceptive or intrusive.
 */
@Composable
fun KidSafeBannerAd(modifier: Modifier = Modifier) {
    if (!AdsConfig.isAdsEnabled) return

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFF1F5F9))
            .border(1.dp, Color(0xFFCBD5E1), RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 12.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color(0xFF94A3B8))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "AD",
                    color = Color.White,
                    fontSize = 10.sp,
                    style = MaterialTheme.typography.labelSmall
                )
            }
            Text(
                text = " Family-Safe Test Ad Space",
                fontSize = 12.sp,
                color = Color(0xFF64748B),
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}
