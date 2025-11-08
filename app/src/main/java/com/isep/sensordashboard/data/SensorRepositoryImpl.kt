package com.isep.sensordashboard.data

import com.isep.sensordashboard.model.SensorReading
import com.isep.sensordashboard.model.SensorType
import com.isep.sensordashboard.sensors.AndroidSensorDataSource
import com.isep.sensordashboard.sensors.SamplingRate
import kotlinx.coroutines.flow.Flow

class SensorRepositoryImpl(
    private val ds: AndroidSensorDataSource
) : SensorRepository {
    override fun availableSensors(): List<SensorType> {
        // TODO(UP-02): Déléguer à la source de données
        // 
        // Indication: Utiliser ds.available() pour obtenir la liste des capteurs disponibles
        // 
        // Exemple: return ds.available()
        TODO("Implement availableSensors()")
    }

    override fun readings(type: SensorType, rate: SamplingRate): Flow<SensorReading> {
        // TODO(UP-03): Déléguer à la source de données
        // 
        // Indication: Utiliser ds.stream(type, rate) pour obtenir le flux de lectures
        // 
        // Exemple: return ds.stream(type, rate)
        TODO("Implement readings()")
    }
}

