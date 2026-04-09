package com.justonedev.androidpractice.features.flow_example.example_two

data class PostState(
    val isLoading: Boolean = false,
    val data: List<Post> = emptyList(),
    val error: String? = null
)