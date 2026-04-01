package com.justonedev.androidpractice.features.notes_app.data

import com.justonedev.androidpractice.features.notes_app.presentation.Note

fun Note.toEntity(): NoteEntity {
    return NoteEntity(
        id = this.id, text = this.text
    )
}

fun NoteEntity.toNote(): Note {
    return Note(
        id = this.id, text = this.text
    )
}
