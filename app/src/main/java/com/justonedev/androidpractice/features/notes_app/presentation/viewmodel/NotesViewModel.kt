package com.justonedev.androidpractice.features.notes_app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.justonedev.androidpractice.features.notes_app.data.NoteEntity
import com.justonedev.androidpractice.features.notes_app.data.NotesDao
import com.justonedev.androidpractice.features.notes_app.data.toEntity
import com.justonedev.androidpractice.features.notes_app.data.toNote
import com.justonedev.androidpractice.features.notes_app.presentation.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class NotesViewModel(
    private val dao: NotesDao,
) : ViewModel() {
    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> = _notes

    private var idCounter = 0

    val localNotes = dao.getNotes().map { list -> list.map { it.toNote() } }

    fun addNote(text: String) {
        val newNote = Note(id = idCounter++, text = text)
        _notes.value += newNote
    }

    fun deleteNote(note: Note) {
        _notes.value -= note
    }

    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.AddNote -> {
//                val newNote = Note(id = idCounter++, text = event.text)
//                _notes.value = _notes.value + newNote

                // WTF is viewmodel scope launch ? Why use is?
                viewModelScope.launch {
                    dao.insert(NoteEntity(text = event.text))
                }
            }


            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    dao.delete(event.note.toEntity())
                }
            }
        }
    }
}