package ru.protei.ilinaav.ui.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import ru.protei.ilinaav.domain.Note
import ru.protei.ilinaav.ui.notes.NotesViewModel
import androidx.compose.runtime.Composable
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color


@Composable
fun NotesScreen(viewModel: NotesViewModel) {
    val noteList by viewModel.noteList.observeAsState(emptyList())
    val currentNote by viewModel.currentNote.observeAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (currentNote == null) {
                        viewModel.addNewNote()
                    } else {
                        viewModel.saveCurrentNote()
                    }
                },
                backgroundColor = MaterialTheme.colorScheme.inversePrimary,
                modifier = Modifier
                    .padding(bottom = 5.dp),
                shape = CircleShape
            ) {
                Icon(
                    imageVector = if (currentNote == null) Icons.Default.Add else Icons.Default.Check,
                    contentDescription = if (currentNote == null) "Add Note" else "Save Note",
                    tint = Color.White
                )
            }
        },
        bottomBar = {
            BottomAppBar(
                backgroundColor = Color.Black,
                modifier = Modifier.height(48.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp)
                ) {
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Назад",
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = {  }) {
                        Icon(
                            Icons.Default.Home,
                            contentDescription = "Домой",
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.Default.KeyboardArrowRight,
                            contentDescription = "Ранее открытые приложения",
                            tint = Color.White
                        )
                    }
                }
            }
        },
        backgroundColor = MaterialTheme.colorScheme.onBackground
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            if (currentNote != null) {
                NoteEditor(
                    note = currentNote!!,
                    onNoteChange = { title, text ->
                        viewModel.updateCurrentNoteTitle(title)
                        viewModel.updateCurrentNoteText(text)
                    }
                )
            } else {
                NotesList(
                    notes = noteList,
                    onNoteClicked = { clickedNote ->
                        viewModel.onNoteClicked(clickedNote)
                    }
                )
            }
        }
    }
}

@Composable
fun NotesList(notes: List<Note>, onNoteClicked: (Note) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .background(MaterialTheme.colorScheme.onBackground),
    ) {
        items(notes) { note ->
            NoteItem(note, onClick = { onNoteClicked(note) })
        }
    }
}

@Composable
fun NoteItem(note: Note, onClick: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor =MaterialTheme.colorScheme.onSurfaceVariant,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = note.title, style = MaterialTheme.typography.bodyLarge,color = MaterialTheme.colorScheme.outline )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = note.text, style = MaterialTheme.typography.bodyLarge,color = MaterialTheme.colorScheme.outline)
        }
    }
}

@Composable
fun NoteEditor(
    note: Note,
    onNoteChange: (String, String) -> Unit
) {
    var titleText by remember { mutableStateOf(note.title) }
    var noteText by remember { mutableStateOf(note.text) }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TextField(
            value = titleText,
            onValueChange = {
                titleText = it
                onNoteChange(it, noteText)
            },
            textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.White ),
            label = { Text(
                text = "Заголовок",
                color = MaterialTheme.colorScheme.outline
            ) },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colorScheme.onSurfaceVariant,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outline,
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 1.dp
                    )
                )
        )
        TextField(
            value = noteText,
            onValueChange = {
                noteText = it
                onNoteChange(titleText, it)
            },
            textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.White),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colorScheme.onSurface
            ),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        )
    }
}