package com.example.jonathan.testandroidstudio.data.repository

import com.example.jonathan.testandroidstudio.data.localdb.KeyIntValueDao
import com.example.jonathan.testandroidstudio.data.localdb.KeyStringValueDao
import com.example.jonathan.testandroidstudio.domain.datamodel.localdb.KeyIntValue
import com.example.jonathan.testandroidstudio.domain.datamodel.localdb.KeyStringValue

class LocalDbRepository(private val keyStringValueDao: KeyStringValueDao,
                        private val keyIntValueDao: KeyIntValueDao
) {
    suspend fun getStringValue(key: String): String? {
        return keyStringValueDao.getValue(key)?.value
    }
    suspend fun insertStringValue(key: String, value: String) {
        keyStringValueDao.insertValue(KeyStringValue(key, value))
    }

    suspend fun getIntValue(key: String): Int? {
        return keyIntValueDao.getValue(key)?.value
    }
    suspend fun insertIntValue(key: String, value: Int) {
        keyIntValueDao.insertValue(KeyIntValue(key, value))
    }
}
