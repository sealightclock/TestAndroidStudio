package com.example.jonathan.testandroidstudio.data.repository

import androidx.lifecycle.LifecycleCoroutineScope
import com.example.jonathan.testandroidstudio.data.remoteserver.KtorClient
import com.example.jonathan.testandroidstudio.domain.datamodel.remoteserver.KeyValuePairs
import kotlinx.coroutines.launch

/**
 * Use an external coroutine scopeto initialize the keyValuePairs property.
 */
class RemoteServerRepository(lifecycleScope: LifecycleCoroutineScope) {
    private lateinit var keyValuePairs: KeyValuePairs

    init {
        lifecycleScope.launch {
            keyValuePairs = KtorClient.getKeyValuePairs(FULL_URL_STR)
        }
    }

    fun getCounter(): Int {
        return keyValuePairs.counter
    }

    fun getNote(): String {
        return keyValuePairs.note
    }

    companion object {
        private const val FULL_URL_STR = "https://raw.githubusercontent.com/sealightclock/ResFileStore/refs/heads/main/key_value_pairs.json"
    }
}
