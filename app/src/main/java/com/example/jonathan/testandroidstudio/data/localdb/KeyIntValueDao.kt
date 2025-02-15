package com.example.jonathan.testandroidstudio.data.localdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jonathan.testandroidstudio.domain.datamodel.localdb.KeyIntValue

@Dao
interface KeyIntValueDao {
    @Query("SELECT * FROM key_int_value_table WHERE `key` = :key LIMIT 1")
    suspend fun getValue(key: String): KeyIntValue?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertValue(keyIntValue: KeyIntValue)
}
