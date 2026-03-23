package com.justonedev.androidpractice.features.notes_app.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.justonedev.androidpractice.features.notes_app.presentation.viewmodel.NotesEvent
import com.justonedev.androidpractice.features.notes_app.presentation.viewmodel.NotesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NotesScreen(viewModel: NotesViewModel = viewModel()) {

    var text by remember { mutableStateOf("") }

    val notes by viewModel.notes.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(), topBar = {
            TopAppBar(title = { Text("Notes App") })
        }) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(15.dp)
                .fillMaxSize()
        ) {

            // TextField
            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Note Description") },
            )

            Spacer(Modifier.height(10.dp))

            Button(
                onClick = {
//                    viewModel.addNote(text)

                    viewModel.onEvent(event = NotesEvent.AddNote(text))
                    text = ""
                }, enabled = text.isNotEmpty()
            ) {
                Text("Add Note")
            }

            Spacer(Modifier.height(12.dp))

            if (notes.isEmpty()) {
                Text("No notes added yet")
                return@Column
            }

            LazyColumn(modifier = Modifier.weight(1f)) {
                items(notes) { note ->

                    Card(
                        shape = RoundedCornerShape(15.dp),
                        elevation = CardDefaults.cardElevation(8.dp),
                        modifier = Modifier.padding(vertical = 5.dp)
                    ) {
                        ListItem(
                            headlineContent = { Text(note.text) },
                            trailingContent = {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete",
                                    modifier = Modifier.clickable {
//                                        viewModel.deleteNote(note)
                                        viewModel.onEvent(event = NotesEvent.DeleteNote(note))
                                    },
                                )
                            },
                        )
                    }
                }
            }
        }
    }
}