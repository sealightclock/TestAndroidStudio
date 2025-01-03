package com.example.jonathan.testandroidstudio.domain.usecase

import com.example.jonathan.testandroidstudio.data.repository.LocalDbRepository

/**
 * This handles the use case of setting "counter"
 */
class SetCounterUseCase(private val repository: LocalDbRepository, private val counter: Int) {
    suspend operator fun invoke() {
        repository.insertIntValue("counter", counter)
    }
}
