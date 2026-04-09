package com.justonedev.androidpractice.features.flow_example.example_one

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FlowDemoScreen(viewModel: FlowDemoViewModel = viewModel()) {

    val context = LocalContext.current

    // 🟢 Flow collection
    val postsFlow = viewModel.getPostsFlow()
    val posts by postsFlow.collectAsState(initial = emptyList())

    // 🔵 StateFlow collection
    val state by viewModel.state.collectAsState()

    // 🔴 SharedFlow collection
    var lastEvent by remember { mutableStateOf("No events yet") }

    LaunchedEffect(Unit) {
        viewModel.events.collect {
            lastEvent = it
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // 🟢 FLOW SECTION (COLD)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Green.copy(alpha = 0.3f))
                .padding(16.dp)
        ) {
            Column {
                Text("Flow (Cold Stream ❄️)", fontWeight = FontWeight.Bold)

                Spacer(Modifier.height(6.dp))

                Text(
                    "• Starts only when collected\n" + "• Emits values over time\n" + "• Each collector gets fresh data (API simulation)",
                    fontSize = 12.sp
                )

                Spacer(Modifier.height(8.dp))

                posts.forEach {
                    Text(it)
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // 🔵 STATEFLOW SECTION (HOT + STATE)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Blue.copy(alpha = 0.3f))
                .padding(16.dp)
        ) {
            Column {
                Text("StateFlow (Hot 🔥 + State)", fontWeight = FontWeight.Bold)

                Spacer(Modifier.height(6.dp))

                Text(
                    "• Always active (hot stream)\n" + "• Holds latest value\n" + "• New collectors get current state instantly",
                    fontSize = 12.sp
                )

                Spacer(Modifier.height(8.dp))

                Text(state)

                Spacer(Modifier.height(8.dp))

                Button(onClick = { viewModel.updateState() }) {
                    Text("Update State")
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // 🔴 SHAREDFLOW SECTION (HOT + EVENTS)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Red.copy(alpha = 0.3f))
                .padding(16.dp)
        ) {
            Column {
                Text("SharedFlow (Hot 🔥 + Events)", fontWeight = FontWeight.Bold)

                Spacer(Modifier.height(6.dp))

                Text(
                    "• Emits one-time events\n" + "• Does NOT hold state\n" + "• New collectors don’t receive old events",
                    fontSize = 12.sp
                )

                Spacer(Modifier.height(8.dp))

                Text(lastEvent)

                Spacer(Modifier.height(8.dp))

                Button(onClick = { viewModel.sendEvent() }) {
                    Text("Send Event")
                }
            }
        }
    }
}