package com.example.jonathan.testandroidstudio.data.localdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jonathan.testandroidstudio.domain.datamodel.localdb.KeyStringValue

@Dao
interface KeyStringValueDao {
    @Query("SELECT * FROM key_string_value_table WHERE `key` = :key LIMIT 1")
    suspend fun getValue(key: String): KeyStringValue?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertValue(keyStringValue: KeyStringValue)
}
