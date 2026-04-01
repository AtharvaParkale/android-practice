package com.justonedev.androidpractice.features.notes_app.data

import android.content.Context
import androidx.room.Room


// WTF is this?
fun provideDatabase(context: Context): NotesDatabase {
    return Room.databaseBuilder(
        context, NotesDatabase::class.java, "notes_db"
    ).build()
}