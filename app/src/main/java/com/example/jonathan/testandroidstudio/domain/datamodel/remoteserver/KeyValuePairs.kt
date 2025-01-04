package com.example.jonathan.testandroidstudio.domain.datamodel.remoteserver

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KeyValuePairs(
    @SerialName("testInteger") val counter: Int = 321,
    @SerialName("testString") val note: String = "initial note from KeyValuePairs",
)
