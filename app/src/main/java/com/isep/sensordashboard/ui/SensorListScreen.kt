package com.isep.sensordashboard.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.isep.sensordashboard.model.SensorType
import com.isep.sensordashboard.UiState

@Composable
fun SensorListScreen(
    state: UiState,
    onSensorSelected: (SensorType) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Sensor Dashboard") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // TODO(UP-05): Display available sensors and call onSensorSelected when user taps one
            // Hint: Use LazyColumn with items() to display the list of sensors from state.available
            // Hint: Use Card or similar composable for each sensor item
            // Hint: Show sensor.displayName and handle onClick to call onSensorSelected
            Text(
                text = "TODO: Display ${state.available.size} available sensors",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

