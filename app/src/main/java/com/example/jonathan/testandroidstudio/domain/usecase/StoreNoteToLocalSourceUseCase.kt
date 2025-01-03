package com.example.jonathan.testandroidstudio.domain.usecase

import com.example.jonathan.testandroidstudio.data.repository.LocalDbRepository

class StoreNoteToLocalSourceUseCase(private val repository: LocalDbRepository, private val note: String) {
    suspend operator fun invoke() {
        repository.insertStringValue("note", note)
    }
}
