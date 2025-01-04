package com.example.jonathan.testandroidstudio.data.repository

import com.example.jonathan.testandroidstudio.data.remoteserver.KtorClient
import com.example.jonathan.testandroidstudio.domain.datamodel.remoteserver.KeyValuePairs

class RemoteServerRepository {
    private lateinit var keyValuePairs: KeyValuePairs

    suspend fun getCounter(): Int {
        keyValuePairs = KtorClient.getKeyValuePairs(FULL_URL_STR)
        return keyValuePairs.counter
    }

    // TODO: This is used, but the editor says no.
    suspend fun getNote(): String {
        keyValuePairs = KtorClient.getKeyValuePairs(FULL_URL_STR)
        return keyValuePairs.note
    }

    companion object {
        private const val FULL_URL_STR = "https://raw.githubusercontent.com/sealightclock/ResFileStore/refs/heads/main/key_value_pairs.json"
    }
}
