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
            // TODO(UP-05): Afficher les valeurs de lastReading
            // 
            // Indications:
            // - Vérifier state.lastReading et state.currentType
            // - Si lastReading != null, afficher chaque valeur avec son label d'axe
            // - Utiliser zip() pour combiner axisLabels et values: 
            //   state.currentType.axisLabels.zip(state.lastReading.values)
            // - Formater les valeurs: "%.2f".format(value)
            // - Afficher "Waiting for values..." si reading est null
            // 
            // Exemple:
            // if (state.lastReading != null && state.currentType != null) {
            //     state.currentType.axisLabels.zip(state.lastReading.values).forEach { (label, value) ->
            //         val suffix = state.currentType.unitSuffix.takeIf { it.isNotEmpty() }?.let { " $it" } ?: ""
            //         Text("$label = ${"%.2f".format(value)}$suffix")
            //     }
            // } else {
            //     Text("Waiting for values...")
            // }
            
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

            // TODO(UP-05): Permettre à l'utilisateur de choisir un SamplingRate et appeler onRateSelected
            // 
            // Indications:
            // - Utiliser LazyRow avec FilterChip pour afficher SamplingRate.entries
            // - Afficher state.rate comme sélectionné (selected = rate == state.rate)
            // - Appeler onRateSelected(rate) quand l'utilisateur sélectionne un taux différent
            // 
            // Exemple de structure:
            // LazyRow(
            //     horizontalArrangement = Arrangement.spacedBy(12.dp)
            // ) {
            //     items(SamplingRate.entries.toTypedArray()) { rate ->
            //         FilterChip(
            //             selected = rate == state.rate,
            //             onClick = { onRateSelected(rate) },
            //             label = { Text(rate.label) }
            //         )
            //     }
            // }
            Text(
                text = "TODO: Display sampling rate selector (current: ${state.rate.label})",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

