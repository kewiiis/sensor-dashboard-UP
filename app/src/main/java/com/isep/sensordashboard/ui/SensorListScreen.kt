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
            // TODO(UP-05): Afficher les capteurs disponibles et appeler onSensorSelected quand l'utilisateur en sélectionne un
            // 
            // Indications:
            // - Utiliser LazyColumn avec items() pour afficher state.available
            // - Utiliser Card avec onClick pour chaque capteur
            // - Afficher sensor.displayName
            // - Gérer onClick pour appeler onSensorSelected(sensor)
            // 
            // Exemple de structure:
            // LazyColumn(
            //     modifier = Modifier.fillMaxSize(),
            //     verticalArrangement = Arrangement.spacedBy(12.dp)
            // ) {
            //     items(state.available) { sensor ->
            //         Card(
            //             onClick = { onSensorSelected(sensor) },
            //             modifier = Modifier.fillMaxWidth()
            //         ) {
            //             Text(
            //                 text = sensor.displayName,
            //                 modifier = Modifier.padding(16.dp)
            //             )
            //         }
            //     }
            // }
            Text(
                text = "TODO: Display ${state.available.size} available sensors",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

