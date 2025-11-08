package com.isep.sensordashboard.model

import android.hardware.Sensor

enum class SensorType(
    val androidType: Int,
    val axisLabels: List<String>,
    val unitSuffix: String,
    val displayName: String,
    val supportsHighSampling: Boolean
) {
    ACCELEROMETER(
        androidType = Sensor.TYPE_ACCELEROMETER,
        axisLabels = listOf("x", "y", "z"),
        unitSuffix = "m/s²",
        displayName = "Accelerometer",
        supportsHighSampling = true
    );
    // TODO(UP-01): Add at least 2 more sensor types (e.g. GYROSCOPE, LIGHT)

    val dimensions: Int = axisLabels.size
}

