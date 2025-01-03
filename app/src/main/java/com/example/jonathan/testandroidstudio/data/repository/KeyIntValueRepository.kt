package com.example.jonathan.testandroidstudio.data.repository

import com.example.jonathan.testandroidstudio.data.localdb.KeyIntValueDao
import com.example.jonathan.testandroidstudio.domain.datamodel.KeyIntValue

class KeyIntValueRepository(private val dao: KeyIntValueDao) {
    suspend fun getValue(key: String): Int? {
        return dao.getValue(key)?.value
    }
    suspend fun insertValue(key: String, value: Int) {
        dao.insertValue(KeyIntValue(key, value))
    }
}
