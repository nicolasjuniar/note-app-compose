package juniar.nicolas.noteappcompose.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import juniar.nicolas.noteappcompose.data.local.NoteDao
import juniar.nicolas.noteappcompose.data.local.NoteDatabase
import juniar.nicolas.noteappcompose.data.repository.NoteRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NoteDatabase =
        Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            "note_db"
        ).build()

    @Provides
    @Singleton
    fun provideNoteDao(db: NoteDatabase) = db.noteDao()

    @Provides
    @Singleton
    fun provideRepository(noteDao: NoteDao) =
        NoteRepository(noteDao)
}