package juniar.nicolas.noteappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import juniar.nicolas.noteappcompose.ui.screen.AddNoteScreen
import juniar.nicolas.noteappcompose.ui.screen.NoteListScreen
import juniar.nicolas.noteappcompose.viewmodel.NoteViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val viewModel: NoteViewModel = hiltViewModel()

            NavHost(navController, startDestination = "list") {
                composable("list") {
                    NoteListScreen(
                        notes = viewModel.notes.collectAsState().value,
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