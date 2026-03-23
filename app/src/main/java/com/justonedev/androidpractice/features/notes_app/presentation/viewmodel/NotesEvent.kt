package com.justonedev.androidpractice.features.notes_app.presentation.viewmodel

import com.justonedev.androidpractice.features.notes_app.presentation.Note

sealed class NotesEvent {
    data class AddNote(val text: String) : NotesEvent()
    data class DeleteNote(val note: Note) : NotesEvent()
}