package com.example.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.model.SavedDrawing

@Database(entities = [SavedDrawing::class], version = 1, exportSchema = false)
abstract class KidsColoringDatabase : RoomDatabase() {

    abstract fun savedDrawingDao(): SavedDrawingDao

    companion object {
        @Volatile
        private var INSTANCE: KidsColoringDatabase? = null

        fun getInstance(context: Context): KidsColoringDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    KidsColoringDatabase::class.java,
                    "kids_coloring_world.db"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}
