package com.example.jonathan.testandroidstudio.domain.usecase

import com.example.jonathan.testandroidstudio.data.repository.LocalDbRepository

/**
 * This handles the use case of setting "note"
 */
class SetNoteUseCase(private val repository: LocalDbRepository, private val note: String) {
    suspend operator fun invoke() {
        repository.insertStringValue("note", note)
    }
}
