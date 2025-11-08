package com.isep.sensordashboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.isep.sensordashboard.data.SensorRepository
import com.isep.sensordashboard.data.SensorRepositoryImpl
import com.isep.sensordashboard.model.SensorReading
import com.isep.sensordashboard.model.SensorType
import com.isep.sensordashboard.sensors.AndroidSensorDataSource
import com.isep.sensordashboard.sensors.SamplingRate
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class UiState(
    val available: List<SensorType> = emptyList(),
    val currentType: SensorType? = null,
    val rate: SamplingRate = SamplingRate.NORMAL,
    val lastReading: SensorReading? = null,
    val error: String? = null,
    val isStreaming: Boolean = false
)

class SensorsViewModel(app: Application) : AndroidViewModel(app) {

    // TODO(UP-04): Initialize SensorRepositoryImpl with AndroidSensorDataSource
    private val repository: SensorRepository = SensorRepositoryImpl(AndroidSensorDataSource(app))

    // TODO(UP-04): Create a StateFlow<UiState> to expose the state to the UI
    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    private var readingsJob: Job? = null

    init {
        // TODO(UP-04): Initialize available sensors on startup
        refreshAvailableSensors()
    }

    fun refreshAvailableSensors() {
        // TODO(UP-04): Implement refreshAvailableSensors()
        // 1. Get available sensors from repository
        // 2. Update state with available sensors
        // 3. Select first sensor if available
        // 4. Start streaming if a sensor is selected
        TODO("Implement refreshAvailableSensors()")
    }

    fun selectSensor(type: SensorType) {
        // TODO(UP-04): Implement selectSensor(type: SensorType)
        // 1. Update state with new currentType
        // 2. Restart streaming with new sensor
        TODO("Implement selectSensor()")
    }

    fun setRate(rate: SamplingRate) {
        // TODO(UP-04): Implement setRate(rate: SamplingRate)
        // 1. Update state with new rate
        // 2. Restart streaming with new rate
        TODO("Implement setRate()")
    }

    private fun restartStream() {
        // TODO(UP-04): Start and stop collecting readings() based on currentType + rate
        // 1. Cancel existing readingsJob if any
        // 2. Get currentType and rate from state
        // 3. If currentType is null, return
        // 4. Update state to isStreaming = true
        // 5. Launch a coroutine to collect from repository.readings()
        // 6. Update state with each reading
        // 7. Handle errors with catch()
        TODO("Implement restartStream()")
    }

    override fun onCleared() {
        readingsJob?.cancel()
        super.onCleared()
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as Application
                SensorsViewModel(application)
            }
        }
    }
}

