package com.example.jonathan.testandroidstudio.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jonathan.testandroidstudio.data.repository.LocalDbRepository
import com.example.jonathan.testandroidstudio.domain.usecase.GetNoteUseCase
import com.example.jonathan.testandroidstudio.domain.usecase.SetNoteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NextViewModel(private val repository: LocalDbRepository) : ViewModel() {
    private val _note: MutableStateFlow<String> = MutableStateFlow("Initial note")
    val note: StateFlow<String>
        get() = _note

    fun updateNote(newNote: String) {
        _note.value = newNote

        // Save the new note to the repository
        setNote()
    }

    fun getNote() {
        viewModelScope.launch {
            _note.value = GetNoteUseCase(repository).invoke()
        }
    }

    private fun setNote() {
        viewModelScope.launch {
            SetNoteUseCase(repository, _note.value).invoke()
        }
    }
}
