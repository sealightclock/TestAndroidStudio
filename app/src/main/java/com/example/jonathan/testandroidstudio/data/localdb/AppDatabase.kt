package com.example.jonathan.testandroidstudio.data.localdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jonathan.testandroidstudio.domain.datamodel.KeyIntValue
import com.example.jonathan.testandroidstudio.domain.datamodel.KeyStringValue

// TODO: Changing this line will require a migration !!!
//   To avoid the migration during testing, uninstall the app.
@Database(entities = [KeyStringValue::class, KeyIntValue::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun keyStringValueDao(): KeyStringValueDao
    abstract fun keyIntValueDao(): KeyIntValueDao

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
