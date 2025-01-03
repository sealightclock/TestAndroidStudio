package com.example.jonathan.testandroidstudio.domain.usecase

import com.example.jonathan.testandroidstudio.data.repository.LocalDbRepository

class FetchCounterFromLocalSourceUseCase(private val repository: LocalDbRepository) {
    suspend operator fun invoke(): Int {
        return repository.getIntValue("counter") ?: 0
    }
}
