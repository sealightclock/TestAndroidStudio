package com.example.jonathan.testandroidstudio.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jonathan.testandroidstudio.data.repository.LocalDbRepository
import com.example.jonathan.testandroidstudio.data.repository.RemoteServerRepository

class WelcomeViewModelFactory(
    private val localDbRepository: LocalDbRepository,
    private val remoteServerRepository: RemoteServerRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WelcomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WelcomeViewModel(
                localDbRepository,
                remoteServerRepository,
                ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
