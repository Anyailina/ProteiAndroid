package ru.protei.ilinaav

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import ru.protei.ilinaav.data.Note
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.protei.ilinaav.ui.theme.IlinaavTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val noteList: ArrayList<Note> = ArrayList()
        noteList.add(Note("Android", "Операционная система"))
        noteList.add(Note("Why I Love Kotlin?", "Lambdos and inline functions"))
        noteList.add(Note("I Love coding", "programming is a hobby"))

        setContent {
            IlinaavTheme {
                NoteList(notes = noteList)
            }
        }
    }
}
@Composable
fun NoteList(notes: List<Note>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(notes) { note ->
            Greeting(title = note.title, text = note.text)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
@Composable
fun Greeting(title: String, text: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            fontSize = 35.sp,
            textAlign = TextAlign.Right
        )
        Text(
            text = text,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}