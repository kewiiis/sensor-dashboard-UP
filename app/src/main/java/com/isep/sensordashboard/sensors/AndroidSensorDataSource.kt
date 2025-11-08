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
        // Hint: Use manager.getDefaultSensor() to check if a sensor type is available
        TODO("Implement available()")
    }

    fun stream(type: SensorType, rate: SamplingRate): Flow<SensorReading> = callbackFlow {
        // TODO(UP-03): Implement stream(type, rate) returning Flow<SensorReading> using SensorEventListener
        // Steps:
        // 1. Get the sensor using manager.getDefaultSensor(type.androidType)
        // 2. Create a SensorEventListener that converts SensorEvent to SensorReading
        // 3. Register the listener with manager.registerListener()
        // 4. Use awaitClose { manager.unregisterListener() } to clean up
        // 5. Handle the sampling period based on rate.requiresHighSamplingPermission and type.supportsHighSampling
        TODO("Implement stream()")
    }
}

