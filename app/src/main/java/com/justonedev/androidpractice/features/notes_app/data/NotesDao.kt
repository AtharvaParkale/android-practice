package com.justonedev.androidpractice.features.notes_app.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    // What is the use of suspend here?
    @Insert
    suspend fun insert(note: NoteEntity)

    @Delete
    suspend fun delete(note: NoteEntity)

    // What is the use of flow here?
    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun getNotes(): Flow<List<NoteEntity>>

}