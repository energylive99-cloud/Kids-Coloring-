package com.example.ui.viewmodel

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.coloring.ColoringEngine
import com.example.coloring.ColoringSessionState
import com.example.data.ColoringCatalog
import com.example.model.BrushSize
import com.example.model.ColoringPage
import com.example.model.ColoringTool
import com.example.model.DrawingStroke
import com.example.model.SavedDrawing
import com.example.storage.DrawingRepository
import com.example.storage.KidsColoringDatabase
import com.example.utils.ShareHelper
import com.example.utils.SoundManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ColoringViewModel(
    val pageId: String,
    private val repository: DrawingRepository,
    private val soundManager: SoundManager
) : ViewModel() {

    private val page: ColoringPage = ColoringCatalog.getPageById(pageId)
        ?: ColoringCatalog.pages.first()

    private val engine = ColoringEngine(page)

    private val _sessionState = MutableStateFlow(engine.getState())
    val sessionState: StateFlow<ColoringSessionState> = _sessionState.asStateFlow()

    private var lastSavedDrawing: SavedDrawing? = null

    fun selectTool(tool: ColoringTool) {
        soundManager.playTap()
        _sessionState.value = engine.setTool(tool)
    }

    fun selectColor(color: Color) {
        soundManager.playColorSelect()
        _sessionState.value = engine.setColor(color)
    }

    fun selectBrushSize(size: BrushSize) {
        soundManager.playTap()
        _sessionState.value = engine.setBrushSize(size)
    }

    fun handleCanvasTap(x: Float, y: Float, width: Float, height: Float) {
        if (_sessionState.value.currentTool == ColoringTool.FILL_BUCKET) {
            val filled = engine.tapToFill(x, y, width, height)
            if (filled) {
                soundManager.playFill()
                _sessionState.value = engine.getState()
            }
        }
    }

    fun addStroke(stroke: DrawingStroke) {
        _sessionState.value = engine.addStroke(stroke)
    }

    fun undo() {
        soundManager.playTap()
        _sessionState.value = engine.undo()
    }

    fun redo() {
        soundManager.playTap()
        _sessionState.value = engine.redo()
    }

    fun clearCanvas() {
        soundManager.playTap()
        _sessionState.value = engine.clearCanvas()
    }

    fun saveDrawing(onSuccess: (SavedDrawing) -> Unit) {
        viewModelScope.launch {
            _sessionState.value = _sessionState.value.copy(isSaving = true)
            val bitmap = engine.exportToBitmap(800, 800)
            val saved = repository.saveDrawing(page, bitmap)
            lastSavedDrawing = saved
            soundManager.playSuccess()
            _sessionState.value = _sessionState.value.copy(
                isSaving = false,
                saveSuccessMessage = "Saved to My Drawings! ⭐"
            )
            onSuccess(saved)
        }
    }

    fun clearSaveMessage() {
        _sessionState.value = _sessionState.value.copy(saveSuccessMessage = null)
    }

    fun shareDrawing(context: Context) {
        viewModelScope.launch {
            soundManager.playTap()
            val saved = lastSavedDrawing ?: run {
                val bitmap = engine.exportToBitmap(800, 800)
                val newSaved = repository.saveDrawing(page, bitmap)
                lastSavedDrawing = newSaved
                newSaved
            }
            ShareHelper.shareDrawing(context, saved.imageFilePath, saved.title)
        }
    }

    companion object {
        fun provideFactory(
            pageId: String,
            context: Context
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val db = KidsColoringDatabase.getInstance(context)
                val repository = DrawingRepository(context, db.savedDrawingDao())
                val soundManager = SoundManager.getInstance(context)
                return ColoringViewModel(pageId, repository, soundManager) as T
            }
        }
    }
}
