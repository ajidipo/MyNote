package com.example.notedipz.data

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addNote(note: Note)

    @Update
    fun updateNote(note: Note)

    @Delete
    fun deleteNote(note: Note)

    @Query("SELECT * FROM note_table ORDER BY ID  ASC")
    fun readAllData():LiveData<List<Note>>
}