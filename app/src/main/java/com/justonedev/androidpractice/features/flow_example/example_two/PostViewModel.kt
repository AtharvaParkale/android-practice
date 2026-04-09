package com.justonedev.androidpractice.features.flow_example.example_two

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostViewModel(
    private val repo: PostRepository
) : ViewModel() {

    private val _state = MutableStateFlow(PostState())
    val state: StateFlow<PostState> = _state

    private val _events = MutableSharedFlow<String>()
    val events = _events

    init {
        fetchPosts()
    }


    private fun fetchPosts() {
        viewModelScope.launch {

            repo.getPosts().collect { newState ->
                _state.value = newState

                if (newState.error != null) {
                    _events.emit("Error: ${newState.error}")
                }
            }
        }
    }
}