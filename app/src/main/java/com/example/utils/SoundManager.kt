package com.example.utils

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioTrack
import android.media.ToneGenerator
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import java.util.concurrent.Executors
import kotlin.math.PI
import kotlin.math.min
import kotlin.math.sin

/**
 * SoundManager helper class for playing simple, playful, and cheerful sound effects
 * for children (button pops, category card chimes, color selects, canvas fills, and celebrations).
 *
 * Utilizes pre-synthesized PCM waveform buffers played via AudioTrack for instantaneous,
 * zero-latency playback with fallback to ToneGenerator on environments without AudioTrack support.
 */
class SoundManager(private val context: Context) {

    private val settingsManager = SettingsManager.getInstance(context)
    private val audioExecutor = Executors.newSingleThreadExecutor()

    private var toneGenerator: ToneGenerator? = null

    // Pre-allocated AudioTracks for zero-latency, playful cartoon sounds
    private var popTrack: AudioTrack? = null
    private var categoryChimeTrack: AudioTrack? = null
    private var successTrack: AudioTrack? = null
    private var fillTrack: AudioTrack? = null
    private var colorSelectTrack: AudioTrack? = null

    init {
        try {
            toneGenerator = ToneGenerator(AudioManager.STREAM_MUSIC, 70)
        } catch (_: Throwable) {
            toneGenerator = null
        }

        // Synthesize audio buffers in the background to avoid any startup hitch
        audioExecutor.execute {
            initializeAudioTracks()
        }
    }

    private fun initializeAudioTracks() {
        try {
            popTrack = createStaticTrack(synthesizeBubblePop())
            categoryChimeTrack = createStaticTrack(synthesizeCategoryChime())
            successTrack = createStaticTrack(synthesizeSuccessFanfare())
            fillTrack = createStaticTrack(synthesizePaintBloop())
            colorSelectTrack = createStaticTrack(synthesizeColorPlink())
        } catch (_: Throwable) {
            // AudioTrack initialization failure falls back to ToneGenerator seamlessly
        }
    }

    /**
     * Playful bubble pop sound for general button clicks, navigation buttons, and taps.
     */
    fun playButtonClick() {
        if (!settingsManager.soundEnabled.value) return
        vibrateGently(15)
        audioExecutor.execute {
            if (!playStaticTrack(popTrack)) {
                playToneFallback(ToneGenerator.TONE_PROP_BEEP, 40)
            }
        }
    }

    /**
     * Alias for general taps.
     */
    fun playTap() = playButtonClick()

    /**
     * Cheerful, bright cartoon chime sound when a child taps a category card.
     */
    fun playCategoryClick() {
        if (!settingsManager.soundEnabled.value) return
        vibrateGently(25)
        audioExecutor.execute {
            if (!playStaticTrack(categoryChimeTrack)) {
                playToneFallback(ToneGenerator.TONE_PROP_ACK, 80)
            }
        }
    }

    /**
     * Alias for card clicks.
     */
    fun playCardClick() = playCategoryClick()

    /**
     * Sweet plink tone when selecting colors on the palette.
     */
    fun playColorSelect() {
        if (!settingsManager.soundEnabled.value) return
        vibrateGently(15)
        audioExecutor.execute {
            if (!playStaticTrack(colorSelectTrack)) {
                playToneFallback(ToneGenerator.TONE_PROP_BEEP, 30)
            }
        }
    }

    /**
     * Satisfying paint bloop sound when filling canvas shapes or drawing.
     */
    fun playFill() {
        if (!settingsManager.soundEnabled.value) return
        vibrateGently(20)
        audioExecutor.execute {
            if (!playStaticTrack(fillTrack)) {
                playToneFallback(ToneGenerator.TONE_CDMA_PIP, 60)
            }
        }
    }

    /**
     * Celebratory sparkling arpeggio chime when completing or saving artwork.
     */
    fun playSuccess() {
        if (!settingsManager.soundEnabled.value) return
        vibrateGently(40)
        audioExecutor.execute {
            if (!playStaticTrack(successTrack)) {
                playToneFallback(ToneGenerator.TONE_PROP_PROMPT, 150)
            }
        }
    }

    /**
     * Soft pop when navigating back.
     */
    fun playBack() {
        if (!settingsManager.soundEnabled.value) return
        vibrateGently(12)
        audioExecutor.execute {
            if (!playStaticTrack(popTrack)) {
                playToneFallback(ToneGenerator.TONE_PROP_BEEP, 30)
            }
        }
    }

    private fun playStaticTrack(track: AudioTrack?): Boolean {
        if (track == null || track.state != AudioTrack.STATE_INITIALIZED) return false
        return try {
            track.stop()
            track.reloadStaticData()
            track.play()
            true
        } catch (_: Throwable) {
            false
        }
    }

    private fun playToneFallback(toneType: Int, durationMs: Int) {
        try {
            toneGenerator?.startTone(toneType, durationMs)
        } catch (_: Throwable) {}
    }

    private fun vibrateGently(durationMs: Long) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? VibratorManager
                val vibrator = vibratorManager?.defaultVibrator
                vibrator?.vibrate(VibrationEffect.createOneShot(durationMs, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                @Suppress("DEPRECATION")
                val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
                @Suppress("DEPRECATION")
                vibrator?.vibrate(durationMs)
            }
        } catch (_: Throwable) {}
    }

    // =========================================================================
    // Waveform Synthesis (PCM 16-bit Mono @ 44.1 kHz)
    // =========================================================================

    private fun createStaticTrack(pcmData: ShortArray, sampleRate: Int = 44100): AudioTrack? {
        return try {
            val bufferSizeBytes = pcmData.size * 2
            val track = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                AudioTrack.Builder()
                    .setAudioAttributes(
                        AudioAttributes.Builder()
                            .setUsage(AudioAttributes.USAGE_GAME)
                            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                            .build()
                    )
                    .setAudioFormat(
                        AudioFormat.Builder()
                            .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                            .setSampleRate(sampleRate)
                            .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                            .build()
                    )
                    .setBufferSizeInBytes(bufferSizeBytes)
                    .setTransferMode(AudioTrack.MODE_STATIC)
                    .build()
            } else {
                @Suppress("DEPRECATION")
                AudioTrack(
                    AudioManager.STREAM_MUSIC,
                    sampleRate,
                    AudioFormat.CHANNEL_OUT_MONO,
                    AudioFormat.ENCODING_PCM_16BIT,
                    bufferSizeBytes,
                    AudioTrack.MODE_STATIC
                )
            }
            track.write(pcmData, 0, pcmData.size)
            track
        } catch (_: Throwable) {
            null
        }
    }

    /**
     * Bubbly rapid upward pitch sweep (400Hz -> 850Hz) with smooth decay.
     */
    private fun synthesizeBubblePop(sampleRate: Int = 44100): ShortArray {
        val durationSec = 0.065
        val numSamples = (sampleRate * durationSec).toInt()
        val pcm = ShortArray(numSamples)
        var phase = 0.0

        for (i in 0 until numSamples) {
            val progress = i.toDouble() / numSamples
            val freq = 420.0 + 460.0 * progress
            phase += 2.0 * PI * freq / sampleRate

            val attack = min(1.0, progress * 16.0)
            val decay = (1.0 - progress) * (1.0 - progress)
            val envelope = attack * decay
            pcm[i] = (sin(phase) * envelope * 24000.0).toInt().coerceIn(-32767, 32767).toShort()
        }
        return pcm
    }

    /**
     * Cheerful dual-tone bell chime (C5: 523Hz -> G5: 784Hz) for category cards.
     */
    private fun synthesizeCategoryChime(sampleRate: Int = 44100): ShortArray {
        val durationSec = 0.15
        val numSamples = (sampleRate * durationSec).toInt()
        val pcm = ShortArray(numSamples)
        var phase1 = 0.0
        var phase2 = 0.0

        for (i in 0 until numSamples) {
            val progress = i.toDouble() / numSamples
            val freq1 = 523.25 // C5
            val freq2 = 783.99 // G5

            phase1 += 2.0 * PI * freq1 / sampleRate
            phase2 += 2.0 * PI * freq2 / sampleRate

            // First note leads, second note shines in middle
            val envelope1 = (1.0 - progress) * (1.0 - progress)
            val envelope2 = if (progress > 0.35) {
                val p2 = (progress - 0.35) / 0.65
                (1.0 - p2) * (1.0 - p2)
            } else 0.0

            val sampleVal = (sin(phase1) * envelope1 * 16000.0) + (sin(phase2) * envelope2 * 18000.0)
            pcm[i] = sampleVal.toInt().coerceIn(-32767, 32767).toShort()
        }
        return pcm
    }

    /**
     * Celebratory 3-note ascending arpeggio for page completion and saves.
     */
    private fun synthesizeSuccessFanfare(sampleRate: Int = 44100): ShortArray {
        val durationSec = 0.26
        val numSamples = (sampleRate * durationSec).toInt()
        val pcm = ShortArray(numSamples)
        val freqs = doubleArrayOf(523.25, 659.25, 783.99, 1046.50) // C5, E5, G5, C6
        var phase = 0.0

        for (i in 0 until numSamples) {
            val progress = i.toDouble() / numSamples
            val noteIdx = min(freqs.lastIndex, (progress * freqs.size).toInt())
            val freq = freqs[noteIdx]
            phase += 2.0 * PI * freq / sampleRate

            val noteProgress = (progress * freqs.size) - noteIdx
            val decay = (1.0 - noteProgress) * 0.8 + 0.2
            val totalDecay = (1.0 - progress * 0.7)
            pcm[i] = (sin(phase) * decay * totalDecay * 22000.0).toInt().coerceIn(-32767, 32767).toShort()
        }
        return pcm
    }

    /**
     * Water-drop paint bloop (frequency falling 850Hz -> 450Hz).
     */
    private fun synthesizePaintBloop(sampleRate: Int = 44100): ShortArray {
        val durationSec = 0.08
        val numSamples = (sampleRate * durationSec).toInt()
        val pcm = ShortArray(numSamples)
        var phase = 0.0

        for (i in 0 until numSamples) {
            val progress = i.toDouble() / numSamples
            val freq = 860.0 - 420.0 * (progress * progress)
            phase += 2.0 * PI * freq / sampleRate

            val envelope = (1.0 - progress) * (1.0 - progress)
            pcm[i] = (sin(phase) * envelope * 24000.0).toInt().coerceIn(-32767, 32767).toShort()
        }
        return pcm
    }

    /**
     * Sweet high plink (780Hz) for palette color picking.
     */
    private fun synthesizeColorPlink(sampleRate: Int = 44100): ShortArray {
        val durationSec = 0.05
        val numSamples = (sampleRate * durationSec).toInt()
        val pcm = ShortArray(numSamples)
        var phase = 0.0

        for (i in 0 until numSamples) {
            val progress = i.toDouble() / numSamples
            phase += 2.0 * PI * 783.99 / sampleRate

            val envelope = (1.0 - progress) * (1.0 - progress)
            pcm[i] = (sin(phase) * envelope * 22000.0).toInt().coerceIn(-32767, 32767).toShort()
        }
        return pcm
    }

    companion object {
        @Volatile
        private var INSTANCE: SoundManager? = null

        fun getInstance(context: Context): SoundManager {
            return INSTANCE ?: synchronized(this) {
                val instance = SoundManager(context.applicationContext)
                INSTANCE = instance
                instance
            }
        }
    }
}

/**
 * Convenient Composable helper to access the application SoundManager instance.
 */
@Composable
fun rememberSoundManager(): SoundManager {
    val context = LocalContext.current
    return remember { SoundManager.getInstance(context) }
}
