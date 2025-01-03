package com.example.jonathan.testandroidstudio.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jonathan.testandroidstudio.data.repository.LocalDbRepository
import com.example.jonathan.testandroidstudio.domain.usecase.GetCounterUseCase
import com.example.jonathan.testandroidstudio.domain.usecase.SetCounterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WelcomeViewModel(private val repository: LocalDbRepository) : ViewModel() {
    private val _counter = MutableStateFlow(0)
    val counter: StateFlow<Int> = _counter

    fun incrementCounter() {
        _counter.value++

        // Save the new counter value to the repository
        setCounter()
    }

    fun getCounter() {
        viewModelScope.launch {
            _counter.value = GetCounterUseCase(repository).invoke()
        }
    }

    private fun setCounter() {
        viewModelScope.launch {
            SetCounterUseCase(repository, _counter.value).invoke()
        }
    }
}
