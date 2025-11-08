package com.isep.sensordashboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.isep.sensordashboard.ui.SensorDetailScreen
import com.isep.sensordashboard.ui.SensorListScreen
import com.isep.sensordashboard.ui.theme.SensorDashboardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SensorDashboardTheme {
                val viewModel: SensorsViewModel = viewModel(factory = SensorsViewModel.Factory)
                val state by viewModel.state.collectAsStateWithLifecycle()
                
                // Simple navigation: show list or detail based on selected sensor
                if (state.currentType != null) {
                    SensorDetailScreen(
                        state = state,
                        onRateSelected = { rate -> viewModel.setRate(rate) }
                    )
                } else {
                    SensorListScreen(
                        state = state,
                        onSensorSelected = { sensor -> viewModel.selectSensor(sensor) }
                    )
                }
            }
        }
    }
}

