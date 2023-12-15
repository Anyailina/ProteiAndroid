package ru.protei.ilinaav

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import ru.protei.ilinaav.ui.notes.NotesScreen
import ru.protei.ilinaav.ui.notes.NotesViewModel


class MainActivity : ComponentActivity() {
    private lateinit var viewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(NotesViewModel::class.java)

        setContent {
            NotesApp(viewModel = viewModel)
        }
    }
}

@Composable
fun NotesApp(viewModel: NotesViewModel) {
    NotesScreen(viewModel = viewModel)
}

@Preview
@Composable
fun PreviewNotesApp() {
    NotesApp(viewModel = NotesViewModel())
}

