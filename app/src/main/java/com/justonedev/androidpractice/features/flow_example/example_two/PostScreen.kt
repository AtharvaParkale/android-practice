package com.justonedev.androidpractice.features.flow_example.example_two

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun PostScreen() {

    val context = LocalContext.current

    val repo = remember {
        PostRepository(ApiClient.api)
    }

    val factory = remember {
        PostViewModelFactory(repo)
    }

    val viewModel: PostViewModel = viewModel(factory = factory)

    val state by viewModel.state.collectAsState()

    // 🔴 Event collector
    LaunchedEffect(Unit) {
        viewModel.events.collect { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    // UI
    when {
        state.isLoading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        state.error != null -> {
            Text("Error: ${state.error}")
        }

        else -> {
            LazyColumn {
                items(state.data) { post ->
                    Text(
                        text = post.title,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}