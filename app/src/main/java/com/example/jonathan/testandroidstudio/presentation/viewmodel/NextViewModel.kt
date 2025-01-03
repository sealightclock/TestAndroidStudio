package com.example.jonathan.testandroidstudio.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jonathan.testandroidstudio.data.repository.KeyStringValueRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NextViewModel(private val repository: KeyStringValueRepository) : ViewModel() {
    private val _note: MutableStateFlow<String> = MutableStateFlow("Initial note")
    val note: StateFlow<String>
        get() = _note
    fun updateNote(newNote: String) {
        _note.value = newNote

        insertKeyStringValue("note", newNote)
    }

    fun fetchKeyStringValue(key: String) {
        viewModelScope.launch {
            val value = repository.getValue(key)
            if (value != null) {
                _note.value = value
            } else {
                _note.value = "value not found for key: $key"
            }
        }
    }

    private fun insertKeyStringValue(key: String, value: String) {
        viewModelScope.launch {
            repository.insertValue(key, value)
        }
    }
}
