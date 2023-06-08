package com.example.notedipz.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class noteViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Note>>
    private val repostiory:NoteRepostiory

    init {
        val noteDao = NoteDatabase.getDatabase(application).NoteDao()
        repostiory = NoteRepostiory(noteDao)
        readAllData = repostiory.readAllData
    }

    fun addNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            repostiory.addNote(note)
        }
    }

    fun updateNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            repostiory.updateNote(note)
        }
    }

    fun deleteNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            repostiory.deleteNote(note)
        }
    }
}