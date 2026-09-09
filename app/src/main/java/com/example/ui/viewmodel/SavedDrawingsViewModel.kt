package com.example.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.model.SavedDrawing
import com.example.storage.DrawingRepository
import com.example.storage.KidsColoringDatabase
import com.example.utils.ShareHelper
import com.example.utils.SoundManager
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SavedDrawingsViewModel(
    private val repository: DrawingRepository,
    private val soundManager: SoundManager
) : ViewModel() {

    val savedDrawings: StateFlow<List<SavedDrawing>> = repository.allDrawings
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun deleteDrawing(drawing: SavedDrawing) {
        viewModelScope.launch {
            soundManager.playTap()
            repository.deleteDrawing(drawing)
        }
    }

    fun clearAll() {
        viewModelScope.launch {
            soundManager.playTap()
            repository.clearAllDrawings()
        }
    }

    fun shareDrawing(context: Context, drawing: SavedDrawing) {
        soundManager.playTap()
        ShareHelper.shareDrawing(context, drawing.imageFilePath, drawing.title)
    }

    companion object {
        fun provideFactory(context: Context): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val db = KidsColoringDatabase.getInstance(context)
                val repository = DrawingRepository(context, db.savedDrawingDao())
                val soundManager = SoundManager.getInstance(context)
                return SavedDrawingsViewModel(repository, soundManager) as T
            }
        }
    }
}
