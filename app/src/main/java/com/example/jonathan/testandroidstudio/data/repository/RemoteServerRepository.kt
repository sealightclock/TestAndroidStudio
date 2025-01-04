package com.example.jonathan.testandroidstudio.data.repository

import com.example.jonathan.testandroidstudio.data.remoteserver.KtorClient
import com.example.jonathan.testandroidstudio.domain.datamodel.KeyValuePairs

class RemoteServerRepository() {
    private var keyValuePairs = KeyValuePairs()

    suspend fun getCounter(): Int {
        keyValuePairs = KtorClient.getKeyValuePairs(FULL_URL_STR)
        return keyValuePairs.counter
    }

    suspend fun getNote(): String {
        keyValuePairs = KtorClient.getKeyValuePairs(FULL_URL_STR)
        return keyValuePairs.note
    }

    companion object {
        private const val FULL_URL_STR = "https://github.com/sealightclock/ResFileStore/blob/main/key_value_pairs.json"
    }
}
