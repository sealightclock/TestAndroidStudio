package com.example.jonathan.testandroidstudio.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jonathan.testandroidstudio.data.repository.LocalDbRepository
import com.example.jonathan.testandroidstudio.data.repository.RemoteServerRepository
import com.example.jonathan.testandroidstudio.domain.usecase.FetchCounterFromLocalSourceUseCase
import com.example.jonathan.testandroidstudio.domain.usecase.FetchCounterFromRemoteServerUseCase
import com.example.jonathan.testandroidstudio.domain.usecase.StoreCounterToLocalSourceUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WelcomeViewModel(
    private val localDbRepository: LocalDbRepository,
    private val remoteServerRepository: RemoteServerRepository,
) : ViewModel() {
    private val _counter = MutableStateFlow(0)
    val counter: StateFlow<Int> = _counter

    fun fetchCounterFromLocalSource() {
        viewModelScope.launch {
            _counter.value = FetchCounterFromLocalSourceUseCase(localDbRepository).invoke()
        }
    }

    private fun storeCounterToLocalSource() {
        viewModelScope.launch {
            StoreCounterToLocalSourceUseCase(localDbRepository, _counter.value).invoke()
        }
    }

    fun incrementCounterByUi() {
        _counter.value++

        storeCounterToLocalSource()
    }

    fun fetchCounterFromRemoteServer() {
        viewModelScope.launch {
            _counter.value = FetchCounterFromRemoteServerUseCase(remoteServerRepository).invoke()

            storeCounterToLocalSource()
        }
    }
}
