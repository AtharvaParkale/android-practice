package com.justonedev.androidpractice.features.notes_app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.justonedev.androidpractice.features.notes_app.presentation.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NotesViewModel : ViewModel() {
    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> = _notes

    private var idCounter = 0

    fun addNote(text: String) {
        val newNote = Note(id = idCounter++, text = text)
        _notes.value = _notes.value + newNote
    }

    fun deleteNote(note: Note) {
        _notes.value = _notes.value - note
    }
}