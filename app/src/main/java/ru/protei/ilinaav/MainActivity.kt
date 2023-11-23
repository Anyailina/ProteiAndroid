package ru.protei.ilinaav

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.protei.ilinaav.ui.theme.IlinaavTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val noteList: ArrayList<Note> = ArrayList()
        noteList.add(Note("Android", "Операционная система"))
        noteList.add(Note("Why I Love Kotlin?", "Lambdos and inline functions"))
        noteList.add(Note("I Love coding", "programming is a hobby"))

        setContent {
            IlinaAvTheme {
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