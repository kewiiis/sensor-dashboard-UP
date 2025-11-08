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
        // TODO(UP-04): Implémenter refreshAvailableSensors()
        // 
        // Étapes:
        // 1. Obtenir les capteurs disponibles: val sensors = repository.availableSensors()
        // 2. Mettre à jour l'état avec les capteurs disponibles
        // 3. Sélectionner le premier capteur s'il y en a un: sensors.firstOrNull()
        // 4. Démarrer le streaming si un capteur est sélectionné: restartStream()
        // 
        // Exemple de structure:
        // val sensors = repository.availableSensors()
        // val selected = sensors.firstOrNull()
        // _state.update { 
        //     it.copy(
        //         available = sensors,
        //         currentType = selected,
        //         error = if (selected == null) "No sensors available" else null
        //     )
        // }
        // if (selected != null) restartStream()
        TODO("Implement refreshAvailableSensors()")
    }

    fun selectSensor(type: SensorType) {
        // TODO(UP-04): Implémenter selectSensor(type: SensorType)
        // 
        // Étapes:
        // 1. Mettre à jour l'état avec le nouveau currentType
        // 2. Réinitialiser lastReading et error
        // 3. Redémarrer le streaming avec le nouveau capteur
        // 
        // Exemple:
        // _state.update { 
        //     it.copy(
        //         currentType = type,
        //         lastReading = null,
        //         error = null
        //     )
        // }
        // restartStream()
        TODO("Implement selectSensor()")
    }

    fun setRate(rate: SamplingRate) {
        // TODO(UP-04): Implémenter setRate(rate: SamplingRate)
        // 
        // Étapes:
        // 1. Mettre à jour l'état avec le nouveau rate
        // 2. Réinitialiser lastReading et error
        // 3. Redémarrer le streaming avec le nouveau taux
        // 
        // Exemple:
        // _state.update { 
        //     it.copy(
        //         rate = rate,
        //         lastReading = null,
        //         error = null
        //     )
        // }
        // restartStream()
        TODO("Implement setRate()")
    }

    private fun restartStream() {
        // TODO(UP-04): Démarrer et arrêter la collecte de readings() basée sur currentType + rate
        // 
        // Étapes:
        // 1. Annuler le job existant: readingsJob?.cancel()
        // 2. Récupérer currentType et rate depuis l'état: val sensorType = _state.value.currentType
        // 3. Si currentType est null, retourner
        // 4. Mettre à jour l'état: isStreaming = true, error = null, lastReading = null
        // 5. Lancer une coroutine pour collecter depuis repository.readings()
        // 6. Mettre à jour l'état avec chaque lecture
        // 7. Gérer les erreurs avec .catch { }
        // 
        // Exemple de structure:
        // readingsJob?.cancel()
        // val sensorType = _state.value.currentType ?: return
        // val rate = _state.value.rate
        // _state.update { it.copy(isStreaming = true, error = null, lastReading = null) }
        // readingsJob = viewModelScope.launch {
        //     repository.readings(sensorType, rate)
        //         .catch { throwable ->
        //             _state.update { 
        //                 it.copy(error = throwable.message, isStreaming = false)
        //             }
        //         }
        //         .collect { reading ->
        //             _state.update { 
        //                 it.copy(lastReading = reading, isStreaming = true, error = null)
        //             }
        //         }
        // }
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

