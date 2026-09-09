package com.example.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a category of coloring pages.
 */
data class Category(
    val id: String,
    val title: String,
    val emoji: String,
    val accentColor: Color,
    val description: String
)

/**
 * Represents a single shape/region in a coloring page that can be filled or outlined.
 */
data class ColoringShape(
    val id: String,
    val name: String,
    val pathBuilder: (width: Float, height: Float) -> Path,
    val defaultFill: Color = Color.White,
    val zIndex: Int = 0
)

/**
 * Represents an individual coloring page.
 */
data class ColoringPage(
    val id: String,
    val categoryId: String,
    val title: String,
    val emoji: String,
    val difficulty: String = "Easy",
    val shapes: List<ColoringShape>
)

/**
 * Represents a freehand brush stroke drawn on the canvas.
 */
data class DrawingStroke(
    val points: List<StrokePoint>,
    val color: Color,
    val strokeWidth: Float,
    val isEraser: Boolean = false
)

data class StrokePoint(
    val x: Float,
    val y: Float
)

/**
 * Brush sizes for children.
 */
enum class BrushSize(val displaySizeDp: Float, val strokeWidthPx: Float, val label: String) {
    SMALL(16f, 12f, "Small"),
    MEDIUM(24f, 26f, "Medium"),
    LARGE(32f, 48f, "Large")
}

/**
 * Active coloring tool.
 */
enum class ColoringTool {
    FILL_BUCKET,
    BRUSH,
    ERASER
}

/**
 * Action recorded for Undo / Redo history.
 */
sealed interface ColoringAction {
    data class FillRegion(
        val shapeId: String,
        val previousColor: Color,
        val newColor: Color
    ) : ColoringAction

    data class AddStroke(
        val stroke: DrawingStroke
    ) : ColoringAction

    data class ClearCanvas(
        val previousFills: Map<String, Color>,
        val previousStrokes: List<DrawingStroke>
    ) : ColoringAction
}

/**
 * Room entity for persisted completed drawings.
 */
@Entity(tableName = "saved_drawings")
data class SavedDrawing(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val pageId: String,
    val title: String,
    val categoryId: String,
    val imageFilePath: String,
    val timestamp: Long = System.currentTimeMillis()
)
