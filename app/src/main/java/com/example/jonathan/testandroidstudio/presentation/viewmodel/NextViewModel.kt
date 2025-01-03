package com.example.jonathan.testandroidstudio.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jonathan.testandroidstudio.data.repository.LocalDbRepository
import com.example.jonathan.testandroidstudio.domain.usecase.FetchNoteFromLocalSourceUseCase
import com.example.jonathan.testandroidstudio.domain.usecase.StoreNoteToLocalSourceUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NextViewModel(private val repository: LocalDbRepository) : ViewModel() {
    private val _note: MutableStateFlow<String> = MutableStateFlow("Initial note from ViewModel")
    val note: StateFlow<String>
        get() = _note

    fun fetchNoteFromLocalSource() {
        viewModelScope.launch {
            _note.value = FetchNoteFromLocalSourceUseCase(repository).invoke()
        }
    }

    private fun storeNoteToLocalSource() {
        viewModelScope.launch {
            StoreNoteToLocalSourceUseCase(repository, _note.value).invoke()
        }
    }

    fun updateNoteByUi(newNote: String) {
        _note.value = newNote

        storeNoteToLocalSource()
    }
}
