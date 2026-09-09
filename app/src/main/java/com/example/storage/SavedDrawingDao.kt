package com.example.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.model.SavedDrawing
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedDrawingDao {

    @Query("SELECT * FROM saved_drawings ORDER BY timestamp DESC")
    fun getAllDrawings(): Flow<List<SavedDrawing>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDrawing(drawing: SavedDrawing): Long

    @Query("DELETE FROM saved_drawings WHERE id = :id")
    suspend fun deleteDrawingById(id: Long)

    @Query("DELETE FROM saved_drawings")
    suspend fun deleteAllDrawings()
}
