package com.example.jonathan.testandroidstudio.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jonathan.testandroidstudio.data.repository.LocalDbRepository
import com.example.jonathan.testandroidstudio.domain.usecase.FetchCounterFromLocalSourceUseCase
import com.example.jonathan.testandroidstudio.domain.usecase.StoreCounterToLocalSourceUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WelcomeViewModel(private val repository: LocalDbRepository) : ViewModel() {
    private val _counter = MutableStateFlow(0)
    val counter: StateFlow<Int> = _counter

    fun fetchCounterFromLocalSource() {
        viewModelScope.launch {
            _counter.value = FetchCounterFromLocalSourceUseCase(repository).invoke()
        }
    }

    private fun storeCounterToLocalSource() {
        viewModelScope.launch {
            StoreCounterToLocalSourceUseCase(repository, _counter.value).invoke()
        }
    }

    fun incrementCounterByUi() {
        _counter.value++

        storeCounterToLocalSource()
    }
}
