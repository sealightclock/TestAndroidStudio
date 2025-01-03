package com.example.jonathan.testandroidstudio.data.localdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jonathan.testandroidstudio.domain.datamodel.KeyStringValue

@Database(entities = [KeyStringValue::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun keyStringValueDao(): KeyStringValueDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
