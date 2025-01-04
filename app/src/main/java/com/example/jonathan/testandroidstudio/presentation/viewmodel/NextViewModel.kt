package com.example.jonathan.testandroidstudio.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jonathan.testandroidstudio.data.repository.LocalDbRepository
import com.example.jonathan.testandroidstudio.data.repository.RemoteServerRepository
import com.example.jonathan.testandroidstudio.domain.usecase.localdb.FetchNoteFromLocalSourceUseCase
import com.example.jonathan.testandroidstudio.domain.usecase.remoteserver.FetchNoteFromRemoteServerUseCase
import com.example.jonathan.testandroidstudio.domain.usecase.localdb.StoreNoteToLocalSourceUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NextViewModel(
    private val localDbRepository: LocalDbRepository,
    private val remoteServerRepository: RemoteServerRepository,
    ) : ViewModel() {
    private val _note: MutableStateFlow<String> = MutableStateFlow("Initial note from ViewModel")
    val note: StateFlow<String>
        get() = _note

    fun fetchNoteFromLocalSource() {
        viewModelScope.launch {
            _note.value = FetchNoteFromLocalSourceUseCase(localDbRepository).invoke()
        }
    }

    private fun storeNoteToLocalSource() {
        viewModelScope.launch {
            StoreNoteToLocalSourceUseCase(localDbRepository, _note.value).invoke()
        }
    }

    fun updateNoteByUi(newNote: String) {
        _note.value = newNote

        storeNoteToLocalSource()
    }

    // TODO: This is used, but the editor says no.
    fun fetchNoteFromRemoteServer() {
        viewModelScope.launch {
            _note.value = FetchNoteFromRemoteServerUseCase(remoteServerRepository).invoke()

            storeNoteToLocalSource()
        }
    }
}
