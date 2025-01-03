package com.example.jonathan.testandroidstudio.domain.usecase

import com.example.jonathan.testandroidstudio.data.repository.LocalDbRepository

/**
 * This handles the use case of getting "note"
 */
class GetNoteUseCase(private val repository: LocalDbRepository) {
    suspend operator fun invoke(): String {
        return repository.getStringValue("note") ?: "No note found !!"
    }
}
