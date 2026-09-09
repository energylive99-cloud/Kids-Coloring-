package com.example.coloring

import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.toArgb
import com.example.data.ShapeGeometry
import com.example.model.BrushSize
import com.example.model.ColoringAction
import com.example.model.ColoringPage
import com.example.model.ColoringShape
import com.example.model.ColoringTool
import com.example.model.DrawingStroke
import com.example.model.StrokePoint

/**
 * State and logic for active coloring session.
 */
data class ColoringSessionState(
    val page: ColoringPage,
    val fills: Map<String, Color> = emptyMap(),
    val strokes: List<DrawingStroke> = emptyList(),
    val undoStack: List<ColoringAction> = emptyList(),
    val redoStack: List<ColoringAction> = emptyList(),
    val currentTool: ColoringTool = ColoringTool.FILL_BUCKET,
    val selectedColor: Color = ColorPalette.defaultPalette[0].color,
    val brushSize: BrushSize = BrushSize.MEDIUM,
    val isSaving: Boolean = false,
    val saveSuccessMessage: String? = null
) {
    val canUndo: Boolean get() = undoStack.isNotEmpty()
    val canRedo: Boolean get() = redoStack.isNotEmpty()
}

class ColoringEngine(
    val page: ColoringPage,
    initialFills: Map<String, Color> = emptyMap()
) {
    private var state: ColoringSessionState

    init {
        val baseFills = mutableMapOf<String, Color>()
        page.shapes.forEach { shape ->
            baseFills[shape.id] = initialFills[shape.id] ?: shape.defaultFill
        }
        state = ColoringSessionState(
            page = page,
            fills = baseFills
        )
    }

    fun getState(): ColoringSessionState = state

    fun setTool(tool: ColoringTool): ColoringSessionState {
        state = state.copy(currentTool = tool)
        return state
    }

    fun setColor(color: Color): ColoringSessionState {
        state = state.copy(
            selectedColor = color,
            currentTool = if (state.currentTool == ColoringTool.ERASER) ColoringTool.BRUSH else state.currentTool
        )
        return state
    }

    fun setBrushSize(size: BrushSize): ColoringSessionState {
        state = state.copy(brushSize = size)
        return state
    }

    /**
     * Attempts to fill a region at touch point (x, y) in canvas dimensions.
     * Returns true if a region was filled.
     */
    fun tapToFill(x: Float, y: Float, width: Float, height: Float): Boolean {
        if (width <= 0f || height <= 0f) return false

        // Sort shapes by zIndex descending so higher layers get tapped first
        val sortedShapes = page.shapes.sortedByDescending { it.zIndex }
        var targetShape: ColoringShape? = null

        for (shape in sortedShapes) {
            val path = shape.pathBuilder(width, height)
            if (ShapeGeometry.isPointInsidePath(path, x, y, width, height)) {
                targetShape = shape
                break
            }
        }

        if (targetShape != null) {
            val oldColor = state.fills[targetShape.id] ?: targetShape.defaultFill
            val newColor = state.selectedColor

            if (oldColor != newColor) {
                val updatedFills = state.fills.toMutableMap()
                updatedFills[targetShape.id] = newColor

                val action = ColoringAction.FillRegion(
                    shapeId = targetShape.id,
                    previousColor = oldColor,
                    newColor = newColor
                )

                state = state.copy(
                    fills = updatedFills,
                    undoStack = state.undoStack + action,
                    redoStack = emptyList() // clear redo on new action
                )
                return true
            }
        }
        return false
    }

    fun addStroke(stroke: DrawingStroke): ColoringSessionState {
        val action = ColoringAction.AddStroke(stroke)
        state = state.copy(
            strokes = state.strokes + stroke,
            undoStack = state.undoStack + action,
            redoStack = emptyList()
        )
        return state
    }

    fun undo(): ColoringSessionState {
        if (!state.canUndo) return state

        val lastAction = state.undoStack.last()
        val remainingUndo = state.undoStack.dropLast(1)

        when (lastAction) {
            is ColoringAction.FillRegion -> {
                val updatedFills = state.fills.toMutableMap()
                updatedFills[lastAction.shapeId] = lastAction.previousColor
                state = state.copy(
                    fills = updatedFills,
                    undoStack = remainingUndo,
                    redoStack = state.redoStack + lastAction
                )
            }
            is ColoringAction.AddStroke -> {
                val updatedStrokes = state.strokes.dropLast(1)
                state = state.copy(
                    strokes = updatedStrokes,
                    undoStack = remainingUndo,
                    redoStack = state.redoStack + lastAction
                )
            }
            is ColoringAction.ClearCanvas -> {
                state = state.copy(
                    fills = lastAction.previousFills,
                    strokes = lastAction.previousStrokes,
                    undoStack = remainingUndo,
                    redoStack = state.redoStack + lastAction
                )
            }
        }
        return state
    }

    fun redo(): ColoringSessionState {
        if (!state.canRedo) return state

        val actionToRedo = state.redoStack.last()
        val remainingRedo = state.redoStack.dropLast(1)

        when (actionToRedo) {
            is ColoringAction.FillRegion -> {
                val updatedFills = state.fills.toMutableMap()
                updatedFills[actionToRedo.shapeId] = actionToRedo.newColor
                state = state.copy(
                    fills = updatedFills,
                    undoStack = state.undoStack + actionToRedo,
                    redoStack = remainingRedo
                )
            }
            is ColoringAction.AddStroke -> {
                state = state.copy(
                    strokes = state.strokes + actionToRedo.stroke,
                    undoStack = state.undoStack + actionToRedo,
                    redoStack = remainingRedo
                )
            }
            is ColoringAction.ClearCanvas -> {
                val defaultFills = page.shapes.associate { it.id to it.defaultFill }
                state = state.copy(
                    fills = defaultFills,
                    strokes = emptyList(),
                    undoStack = state.undoStack + actionToRedo,
                    redoStack = remainingRedo
                )
            }
        }
        return state
    }

    fun clearCanvas(): ColoringSessionState {
        val defaultFills = page.shapes.associate { it.id to it.defaultFill }
        val action = ColoringAction.ClearCanvas(
            previousFills = state.fills,
            previousStrokes = state.strokes
        )
        state = state.copy(
            fills = defaultFills,
            strokes = emptyList(),
            undoStack = state.undoStack + action,
            redoStack = emptyList()
        )
        return state
    }

    /**
     * Renders the full drawing to an Android Bitmap for saving and sharing.
     */
    fun exportToBitmap(width: Int = 800, height: Int = 800): Bitmap {
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = android.graphics.Canvas(bitmap)

        // 1. Background white paper
        canvas.drawColor(android.graphics.Color.WHITE)

        val wFloat = width.toFloat()
        val hFloat = height.toFloat()

        // 2. Draw filled shapes sorted by zIndex
        val fillPaint = Paint().apply {
            isAntiAlias = true
            style = Paint.Style.FILL
        }

        val sortedShapes = page.shapes.sortedBy { it.zIndex }
        for (shape in sortedShapes) {
            val color = state.fills[shape.id] ?: shape.defaultFill
            fillPaint.color = color.toArgb()
            val path = shape.pathBuilder(wFloat, hFloat).asAndroidPath()
            canvas.drawPath(path, fillPaint)
        }

        // 3. Draw freehand brush strokes
        val strokePaint = Paint().apply {
            isAntiAlias = true
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
        }

        for (stroke in state.strokes) {
            if (stroke.points.size < 2) continue

            strokePaint.strokeWidth = stroke.strokeWidth
            if (stroke.isEraser) {
                // Eraser uses white or draws over
                strokePaint.color = android.graphics.Color.WHITE
                strokePaint.xfermode = null
            } else {
                strokePaint.color = stroke.color.toArgb()
                strokePaint.xfermode = null
            }

            val strokePath = android.graphics.Path()
            val first = stroke.points.first()
            strokePath.moveTo(first.x, first.y)
            for (i in 1 until stroke.points.size) {
                val pt = stroke.points[i]
                strokePath.lineTo(pt.x, pt.y)
            }
            canvas.drawPath(strokePath, strokePaint)
        }

        // 4. Draw bold outline on top for crisp coloring book appearance
        val outlinePaint = Paint().apply {
            isAntiAlias = true
            style = Paint.Style.STROKE
            color = android.graphics.Color.parseColor("#212121")
            strokeWidth = 6f
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
        }

        for (shape in sortedShapes) {
            val path = shape.pathBuilder(wFloat, hFloat).asAndroidPath()
            canvas.drawPath(path, outlinePaint)
        }

        return bitmap
    }
}
