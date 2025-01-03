package com.example.jonathan.testandroidstudio.data.repository

import com.example.jonathan.testandroidstudio.data.localdb.KeyStringValueDao
import com.example.jonathan.testandroidstudio.domain.datamodel.KeyStringValue

class KeyStringValueRepository(private val dao: KeyStringValueDao) {
    suspend fun getValue(key: String): String? {
        return dao.getValue(key)?.value
    }
    suspend fun insertValue(key: String, value: String) {
        dao.insertValue(KeyStringValue(key, value))
    }
}
