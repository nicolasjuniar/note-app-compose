package juniar.nicolas.noteappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import juniar.nicolas.noteappcompose.ui.AddNoteScreen
import juniar.nicolas.noteappcompose.ui.NoteListScreen
import juniar.nicolas.noteappcompose.viewmodel.NoteViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val viewModel: NoteViewModel = viewModel()

            NavHost(navController, startDestination = "list") {
                composable("list") {
                    NoteListScreen(
                        notes = viewModel.notes,
                        onAddClick = { navController.navigate("add") },
                        onDeleteClick = { viewModel.deleteNote(it) }
                    )
                }
                composable("add") {
                    AddNoteScreen(
                        onSaveClick = { title, content -> viewModel.addNote(title, content) },
                        onBackClick = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}