package com.example.jonathan.testandroidstudio.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jonathan.testandroidstudio.data.repository.LocalDbRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WelcomeViewModel(private val repository: LocalDbRepository) : ViewModel() {
    private val _counter = MutableStateFlow(0)
    val counter: StateFlow<Int> = _counter
    fun incrementCounter() {
        _counter.value++

        insertKeyIntValue("counter", _counter.value)
    }

    fun fetchKeyIntValue(key: String) {
        viewModelScope.launch {
            val value = repository.getIntValue(key)
            if (value != null) {
                _counter.value = value
            } else {
                _counter.value = 0
            }
        }
    }

    private fun insertKeyIntValue(key: String, value: Int) {
        viewModelScope.launch {
            repository.insertIntValue(key, value)
        }
    }
}
