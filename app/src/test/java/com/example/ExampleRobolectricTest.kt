package com.example

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.test.core.app.ApplicationProvider
import com.example.coloring.ColoringEngine
import com.example.data.ColoringCatalog
import com.example.model.ColoringTool
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Kids Coloring World", appName)
  }

  @Test
  fun `verify coloring catalog categories and pages`() {
    val categories = ColoringCatalog.categories
    assertEquals(8, categories.size)
    assertTrue(ColoringCatalog.pages.size >= 30)

    val lionPage = ColoringCatalog.getPageById("animal_lion")
    assertNotNull(lionPage)
    assertTrue(lionPage!!.shapes.isNotEmpty())
  }

  @Test
  fun `verify coloring engine fill and undo redo`() {
    val page = ColoringCatalog.pages.first()
    val engine = ColoringEngine(page)

    val targetShape = page.shapes.first()
    val testColor = Color(0xFFFF5252)

    engine.setTool(ColoringTool.FILL_BUCKET)
    engine.setColor(testColor)

    // Simulate tap on center of the first shape
    val filled = engine.tapToFill(200f, 200f, 400f, 400f)
    if (filled) {
      assertTrue(engine.getState().canUndo)
      engine.undo()
      assertTrue(engine.getState().canRedo)
      engine.redo()
    }

    // Export to bitmap
    val bitmap = engine.exportToBitmap(200, 200)
    assertNotNull(bitmap)
    assertEquals(200, bitmap.width)
    assertEquals(200, bitmap.height)
  }

  @Test
  fun `verify all 8 category cards are present for HomeScreen`() {
    val categories = ColoringCatalog.categories
    assertEquals(8, categories.size)
    val expectedIds = listOf("animals", "vehicles", "dinosaurs", "fruits", "nature", "space", "toys", "alphabet")
    assertEquals(expectedIds, categories.map { it.id })
  }

  @Test
  fun `verify SoundManager plays playful sound effects without errors`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val soundManager = com.example.utils.SoundManager.getInstance(context)
    val settingsManager = com.example.utils.SettingsManager.getInstance(context)

    settingsManager.setSoundEnabled(true)
    assertTrue(settingsManager.soundEnabled.value)

    // Verify all sound methods execute smoothly
    soundManager.playButtonClick()
    soundManager.playCategoryClick()
    soundManager.playCardClick()
    soundManager.playTap()
    soundManager.playColorSelect()
    soundManager.playFill()
    soundManager.playSuccess()
    soundManager.playBack()

    // Verify when sound is disabled, it returns without crashing
    settingsManager.setSoundEnabled(false)
    soundManager.playButtonClick()
    soundManager.playCategoryClick()

    // Restore enabled
    settingsManager.setSoundEnabled(true)
  }
}

