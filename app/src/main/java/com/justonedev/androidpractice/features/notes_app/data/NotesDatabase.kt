package com.justonedev.androidpractice.features.notes_app.data

import androidx.room.Database
import androidx.room.RoomDatabase

// WTF is entities and version here?
@Database(
    entities = [NoteEntity::class],
    version = 1,
)
abstract class NotesDatabase : RoomDatabase() {
    // WTF is abstract fun here?
    abstract fun notesDao(): NotesDao
}