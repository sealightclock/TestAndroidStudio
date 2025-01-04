package com.example.jonathan.testandroidstudio.domain.usecase.localdb

import com.example.jonathan.testandroidstudio.data.repository.LocalDbRepository

class StoreCounterToLocalSourceUseCase(private val repository: LocalDbRepository, private val counter: Int) {
    suspend operator fun invoke() {
        repository.insertIntValue("counter", counter)
    }
}
