package ru.protei.ilinaav.ui.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.protei.ilinaav.domain.Note

class NotesViewModel : ViewModel() {
    private val _noteList = MutableLiveData<List<Note>>(emptyList())
    val noteList: LiveData<List<Note>> get() = _noteList

    private val _currentNote = MutableLiveData<Note?>(null)
    val currentNote: LiveData<Note?> get() = _currentNote

    fun setCurrentNoteForEditing(note: Note?) {
        _currentNote.value = note
    }

    fun addNewNote() {
        val newNote = Note("", "")
        setCurrentNoteForEditing(newNote)
    }

    fun updateCurrentNoteTitle(title: String) {
        _currentNote.value?.title = title
    }

    fun updateCurrentNoteText(text: String) {
        _currentNote.value?.text = text
    }

    fun saveCurrentNote() {
        val currentNoteValue = _currentNote.value
        currentNoteValue?.let { note ->
            val updatedList = _noteList.value.orEmpty().toMutableList()
            if (!updatedList.contains(note)) {
                updatedList.add(note)
            }
            _noteList.value = updatedList
            setCurrentNoteForEditing(null)
        }
    }

    fun onNoteClicked(note: Note) {
        setCurrentNoteForEditing(note)
    }
}