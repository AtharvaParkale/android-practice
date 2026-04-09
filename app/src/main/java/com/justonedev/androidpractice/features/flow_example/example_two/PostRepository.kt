package com.justonedev.androidpractice.features.flow_example.example_two

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PostRepository(
    private val api: ApiService
) {

    fun getPosts(): Flow<PostState> = flow {

        emit(PostState(isLoading = true))

        try {
            while(true){
                val posts = api.getPosts()
                emit(PostState(data = posts))
                delay(5000)
            }
        } catch (e: Exception) {
            emit(PostState(error = e.message))
        }
    }
}