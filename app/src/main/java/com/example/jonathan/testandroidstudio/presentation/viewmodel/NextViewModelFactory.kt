package com.example.jonathan.testandroidstudio.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jonathan.testandroidstudio.data.repository.LocalDbRepository

class NextViewModelFactory(private val repository: LocalDbRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NextViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NextViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
