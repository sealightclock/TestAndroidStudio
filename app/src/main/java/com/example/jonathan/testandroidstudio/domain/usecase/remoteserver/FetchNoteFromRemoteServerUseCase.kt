package com.example.jonathan.testandroidstudio.domain.usecase.remoteserver

import com.example.jonathan.testandroidstudio.data.repository.RemoteServerRepository

class FetchNoteFromRemoteServerUseCase(private val repository: RemoteServerRepository) {
    suspend operator fun invoke(): String {
        return repository.getNote()
    }
}

