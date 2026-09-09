package com.example.storage

import android.content.Context
import android.graphics.Bitmap
import com.example.model.ColoringPage
import com.example.model.SavedDrawing
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

class DrawingRepository(
    private val context: Context,
    private val dao: SavedDrawingDao
) {
    val allDrawings: Flow<List<SavedDrawing>> = dao.getAllDrawings()

    /**
     * Saves a rendered coloring bitmap to internal app storage and records it in Room database.
     */
    suspend fun saveDrawing(page: ColoringPage, bitmap: Bitmap): SavedDrawing = withContext(Dispatchers.IO) {
        val drawingsDir = File(context.filesDir, "drawings").apply {
            if (!exists()) mkdirs()
        }

        val filename = "drawing_${page.id}_${System.currentTimeMillis()}.png"
        val file = File(drawingsDir, filename)

        FileOutputStream(file).use { out ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
        }

        val entity = SavedDrawing(
            pageId = page.id,
            title = page.title,
            categoryId = page.categoryId,
            imageFilePath = file.absolutePath,
            timestamp = System.currentTimeMillis()
        )

        val insertedId = dao.insertDrawing(entity)
        entity.copy(id = insertedId)
    }

    /**
     * Deletes a drawing from internal storage and the database.
     */
    suspend fun deleteDrawing(drawing: SavedDrawing) = withContext(Dispatchers.IO) {
        try {
            val file = File(drawing.imageFilePath)
            if (file.exists()) {
                file.delete()
            }
        } catch (_: Exception) {
            // Ignore file deletion error
        }
        dao.deleteDrawingById(drawing.id)
    }

    /**
     * Clears all saved drawings from disk and database.
     */
    suspend fun clearAllDrawings() = withContext(Dispatchers.IO) {
        try {
            val drawingsDir = File(context.filesDir, "drawings")
            drawingsDir.listFiles()?.forEach { it.delete() }
        } catch (_: Exception) {
            // Ignore error
        }
        dao.deleteAllDrawings()
    }
}
