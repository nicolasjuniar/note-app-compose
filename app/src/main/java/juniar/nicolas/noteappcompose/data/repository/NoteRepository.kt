package juniar.nicolas.noteappcompose.data.repository

import juniar.nicolas.noteappcompose.data.local.NoteDao
import juniar.nicolas.noteappcompose.data.local.NoteEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteDao: NoteDao
) {
    fun getAllNotes(): Flow<List<NoteEntity>> = noteDao.getAllNotes()
    suspend fun addNote(note: NoteEntity) = noteDao.insertNote(note)
    suspend fun deleteNote(note: NoteEntity) = noteDao.deleteNote(note)
}