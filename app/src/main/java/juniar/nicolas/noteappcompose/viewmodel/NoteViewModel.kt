package juniar.nicolas.noteappcompose.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import juniar.nicolas.noteappcompose.model.Note

class NoteViewModel : ViewModel() {
    private var nextId = 1
    private val _notes = mutableStateListOf<Note>()
    val notes: List<Note> get() = _notes

    fun addNote(title: String, content: String) {
        if (title.isBlank() && content.isBlank()) return
        _notes.add(Note(nextId++, title, content))
    }

    fun deleteNote(note: Note) {
        _notes.remove(note)
    }
}