package com.justonedev.androidpractice.features.flow_example.example_two

import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<Post>
}

