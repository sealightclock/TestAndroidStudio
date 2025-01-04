package com.example.jonathan.testandroidstudio.domain.usecase.remoteserver

import com.example.jonathan.testandroidstudio.data.repository.RemoteServerRepository

class FetchCounterFromRemoteServerUseCase(private val repository: RemoteServerRepository) {
    suspend operator fun invoke(): Int {
        return repository.getCounter()
    }
}

