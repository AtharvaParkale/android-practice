package com.justonedev.androidpractice.features.notes_app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.justonedev.androidpractice.features.notes_app.data.NotesDao

class NotesViewModelFactory(
    private val dao: NotesDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NotesViewModel(dao) as T
    }
}