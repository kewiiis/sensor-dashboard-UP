package com.isep.sensordashboard.sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import com.isep.sensordashboard.model.SensorReading
import com.isep.sensordashboard.model.SensorType
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class AndroidSensorDataSource(
    context: Context
) {
    // TODO(UP-02): Initialize SensorManager from context
    private val manager: SensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    fun available(): List<SensorType> {
        // TODO(UP-02): Implement available() to return supported SensorType values based on real sensors
        // 
        // Indications:
        // - Utiliser SensorType.entries pour obtenir tous les types de capteurs définis
        // - Filtrer avec .filter { } pour ne garder que ceux disponibles sur l'appareil
        // - Vérifier la disponibilité avec: manager.getDefaultSensor(it.androidType) != null
        // 
        // Exemple de code:
        // return SensorType.entries.filter { sensorType ->
        //     manager.getDefaultSensor(sensorType.androidType) != null
        // }
        TODO("Implement available()")
    }

    fun stream(type: SensorType, rate: SamplingRate): Flow<SensorReading> = callbackFlow {
        // TODO(UP-03): Implement stream(type, rate) returning Flow<SensorReading> using SensorEventListener
        // 
        // Étapes à suivre:
        // 
        // 1. Récupérer le capteur:
        //    val sensor: Sensor? = manager.getDefaultSensor(type.androidType)
        //    if (sensor == null) {
        //        close(IllegalStateException("Sensor not available"))
        //        return@callbackFlow
        //    }
        //
        // 2. Créer un SensorEventListener qui convertit SensorEvent en SensorReading:
        //    val listener = object : SensorEventListener {
        //        override fun onSensorChanged(event: SensorEvent) {
        //            val values = List(type.dimensions) { index ->
        //                event.values.getOrNull(index) ?: 0f
        //            }
        //            trySend(SensorReading(
        //                type = type,
        //                values = values,
        //                timestampNanos = event.timestamp
        //            ))
        //        }
        //        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) = Unit
        //    }
        //
        // 3. Calculer la période d'échantillonnage:
        //    val samplingPeriodUs = if (rate.requiresHighSamplingPermission && type.supportsHighSampling) {
        //        0  // Mode haute fréquence
        //    } else {
        //        rate.samplingPeriodMicros
        //    }
        //
        // 4. Enregistrer le listener:
        //    manager.registerListener(listener, sensor, samplingPeriodUs, 0)
        //
        // 5. Nettoyer à la fermeture:
        //    awaitClose { manager.unregisterListener(listener) }
        TODO("Implement stream()")
    }
}

