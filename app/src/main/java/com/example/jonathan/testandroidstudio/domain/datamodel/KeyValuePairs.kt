package com.example.jonathan.testandroidstudio.domain.datamodel

import kotlinx.serialization.Serializable

@Serializable
data class KeyValuePairs(
    val counter: Int = 321,
    val note: String = "initial note from KeyValuePairs",
)
