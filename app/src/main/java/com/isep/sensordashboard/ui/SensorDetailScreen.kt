package com.isep.sensordashboard.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.isep.sensordashboard.sensors.SamplingRate
import com.isep.sensordashboard.UiState

@Composable
fun SensorDetailScreen(
    state: UiState,
    onRateSelected: (SamplingRate) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Sensor Detail") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // TODO(UP-05): Display lastReading values
            // Hint: Check state.lastReading and state.currentType
            // Hint: Display each value with its axis label (e.g., "x = 0.00 m/s²")
            // Hint: Show a message if reading is null (e.g., "Waiting for values...")
            
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = state.currentType?.displayName ?: "No sensor selected",
                        style = MaterialTheme.typography.titleLarge
                    )
                    if (state.lastReading != null) {
                        Text(
                            text = "TODO: Display reading values",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    } else {
                        Text(
                            text = "Waiting for values...",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

            // TODO(UP-05): Allow user to pick a SamplingRate and call onRateSelected
            // Hint: Use LazyRow with FilterChip or similar to display SamplingRate.entries
            // Hint: Show state.rate as selected
            // Hint: Call onRateSelected when user selects a different rate
            Text(
                text = "TODO: Display sampling rate selector (current: ${state.rate.label})",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

