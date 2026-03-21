package com.justonedev.androidpractice.features.notes_app.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NotesScreen() {
    val text = remember { mutableStateOf("Hello") }
    val notes = remember { mutableStateListOf<String>() } // Must be compose state for UI to update


    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(
            title = {
                Text(
                    "Notes App", style = TextStyle(
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Medium,
                    )
                )
            },
        )
    }) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            TextField(
                value = text.value,
                onValueChange = { text.value = it },
                label = { Text("Note Description") },
            )
            Spacer(Modifier.height(10.dp))
            Button(onClick = {
                notes.add(text.value)

                text.value = ""
            }) {
                Text("Click")
            }
            Spacer(Modifier.height(12.dp))

            if (notes.isEmpty()) {
                Text("No notes added yet")

                return@Column
            }

            LazyColumn(modifier = Modifier.weight(1f)) { // Here always height must be specified
                items(notes) { note ->
                    ListItem(
                        headlineContent = { Text(text = note) },
                        leadingContent = {
                            Icon(
                                imageVector = Icons.Filled.Delete,
                                contentDescription = "Delete Note",
                                modifier = Modifier.clickable {
                                    // handle delete
                                    notes.remove(note)
                                },
                            )
                        },
                    )
                }
            }
        }
    }
}