package com.example.jonathan.testandroidstudio.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NextViewModel : ViewModel() {
    private val _note: MutableStateFlow<String> = MutableStateFlow("Initial note")
    val note: StateFlow<String>
        get() = _note
    fun updateNote(newNote: String) {
        _note.value = newNote
    }
}
