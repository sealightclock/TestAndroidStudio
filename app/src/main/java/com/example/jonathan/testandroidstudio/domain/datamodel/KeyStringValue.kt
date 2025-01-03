package com.example.jonathan.testandroidstudio.domain.datamodel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "key_string_value_table")
data class KeyStringValue(
    @PrimaryKey val key: String,
    val value: String
)
