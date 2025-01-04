package com.example.jonathan.testandroidstudio.domain.datamodel.localdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "key_int_value_table")
data class KeyIntValue(
    @PrimaryKey val key: String,
    val value: Int
)
