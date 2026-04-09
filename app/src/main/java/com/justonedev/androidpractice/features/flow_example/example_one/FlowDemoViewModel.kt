package com.justonedev.androidpractice.features.flow_example.example_one

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class FlowDemoViewModel : ViewModel() {

    // 🟢 FLOW (Cold → API simulation)
    fun getPostsFlow(): Flow<List<String>> = flow {
        while (true) {
            val data = listOf("Post ${System.currentTimeMillis()}")
            emit(data)
            delay(3000)
        }
    }

    // 🔵 STATEFLOW (State holder)
    private val _state = MutableStateFlow("Initial State")
    val state: StateFlow<String> = _state

    fun updateState() {
        _state.value = "Updated at ${System.currentTimeMillis()}"
    }

    // 🔴 SHAREDFLOW (Events)
    private val _events = MutableSharedFlow<String>()
    val events = _events

    fun sendEvent() {
        viewModelScope.launch {
            _events.emit("Event at ${System.currentTimeMillis()}")
        }
    }
}