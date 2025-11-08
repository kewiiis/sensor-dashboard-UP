# Sensor Dashboard — Unfinished Project (UP)

## Overview

This repository contains an **unfinished Android project** designed as a lab exercise. The project implements a sensor dashboard application that displays real-time sensor readings from Android device sensors.

The architecture is aligned with the **sensor-dashboard-demo** reference project. Students should use the demo as a reference implementation while completing the TODOs in this project.

## Goal

Complete the missing features during the in-class exercise to create a working sensor dashboard application.

## Setup

- **Android Studio** (with SDK 36)
- **Kotlin** + **Jetpack Compose**
- **Real Android device** with sensors (emulator may have limited sensor support)

## Architecture

The project follows a clean architecture pattern:

```
com.isep.sensordashboard/
├── model/          # Data models (SensorType, SensorReading)
├── sensors/        # Sensor data source (AndroidSensorDataSource, SamplingRate)
├── data/           # Repository layer (SensorRepository, SensorRepositoryImpl)
├── viewmodel/      # ViewModel (SensorsViewModel with UiState)
└── ui/             # Compose UI screens (SensorListScreen, SensorDetailScreen)
```

## TODOs to Complete

### UP-01: Extend SensorType Enum

**File:** `app/src/main/java/com/isep/sensordashboard/model/SensorType.kt`

**Task:** Add at least 2 more sensor types (e.g., `GYROSCOPE`, `LIGHT`).

**Requirements:**
- Follow the same pattern as `ACCELEROMETER`
- Set appropriate `androidType`, `axisLabels`, `unitSuffix`, `displayName`, and `supportsHighSampling`
- Reference Android Sensor constants (e.g., `Sensor.TYPE_GYROSCOPE`, `Sensor.TYPE_LIGHT`)

**Reference:** Check `sensor-dashboard-demo` for examples.

---

### UP-02: Implement AndroidSensorDataSource

**Files:**
- `app/src/main/java/com/isep/sensordashboard/sensors/AndroidSensorDataSource.kt`
- `app/src/main/java/com/isep/sensordashboard/data/SensorRepositoryImpl.kt`

**Tasks:**

1. **Initialize SensorManager** in `AndroidSensorDataSource`:
   ```kotlin
   private val manager: SensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
   ```

2. **Implement `available()`** method:
   - Filter `SensorType.entries` to return only sensors available on the device
   - Use `manager.getDefaultSensor(type.androidType)` to check availability

3. **Implement `SensorRepositoryImpl.availableSensors()`**:
   - Delegate to `ds.available()`

**Reference:** See `sensor-dashboard-demo/app/src/main/java/com/isep/sensordashboard/sensors/AndroidSensorDataSource.kt`

---

### UP-03: Implement Sensor Streaming with Flow

**Files:**
- `app/src/main/java/com/isep/sensordashboard/sensors/AndroidSensorDataSource.kt`
- `app/src/main/java/com/isep/sensordashboard/data/SensorRepositoryImpl.kt`

**Tasks:**

1. **Implement `stream()`** in `AndroidSensorDataSource`:
   - Get the sensor using `manager.getDefaultSensor(type.androidType)`
   - Create a `SensorEventListener` that converts `SensorEvent` to `SensorReading`
   - Register the listener with `manager.registerListener()`
   - Use `awaitClose { manager.unregisterListener() }` for cleanup
   - Handle sampling period: if `rate.requiresHighSamplingPermission && type.supportsHighSampling`, use `0`; otherwise use `rate.samplingPeriodMicros`

2. **Implement `SensorRepositoryImpl.readings()`**:
   - Delegate to `ds.stream(type, rate)`

**Reference:** See the demo's `AndroidSensorDataSource.stream()` implementation.

---

### UP-04: Complete SensorsViewModel

**File:** `app/src/main/java/com/isep/sensordashboard/SensorsViewModel.kt`

**Tasks:**

1. **Initialize repository and state:**
   ```kotlin
   private val repository: SensorRepository = SensorRepositoryImpl(AndroidSensorDataSource(app))
   private val _state = MutableStateFlow(UiState())
   val state: StateFlow<UiState> = _state.asStateFlow()
   ```

2. **Implement `refreshAvailableSensors()`**:
   - Get available sensors from repository
   - Update state with available sensors
   - Select first sensor if available
   - Start streaming if a sensor is selected

3. **Implement `selectSensor()`**:
   - Update state with new `currentType`
   - Call `restartStream()`

4. **Implement `setRate()`**:
   - Update state with new `rate`
   - Call `restartStream()`

5. **Implement `restartStream()`**:
   - Cancel existing `readingsJob` if any
   - Get `currentType` and `rate` from state
   - If `currentType` is null, return
   - Update state to `isStreaming = true`
   - Launch a coroutine to collect from `repository.readings()`
   - Update state with each reading using `_state.update { ... }`
   - Handle errors with `.catch { ... }`

6. **Initialize sensors in `init` block:**
   - Call `refreshAvailableSensors()`

**Reference:** See `sensor-dashboard-demo/app/src/main/java/com/isep/sensordashboard/SensorsViewModel.kt`

---

### UP-05: Implement UI Screens

**Files:**
- `app/src/main/java/com/isep/sensordashboard/ui/SensorListScreen.kt`
- `app/src/main/java/com/isep/sensordashboard/ui/SensorDetailScreen.kt`

**Tasks:**

1. **SensorListScreen:**
   - Display available sensors from `state.available`
   - Use `LazyColumn` with `items()` to display the list
   - Use `Card` or similar composable for each sensor item
   - Show `sensor.displayName`
   - Handle `onClick` to call `onSensorSelected(sensor)`

2. **SensorDetailScreen:**
   - Display `lastReading` values:
     - Check `state.lastReading` and `state.currentType`
     - Display each value with its axis label (e.g., "x = 0.00 m/s²")
     - Show "Waiting for values..." if reading is null
   - Display sampling rate selector:
     - Use `LazyRow` with `FilterChip` to display `SamplingRate.entries`
     - Show `state.rate` as selected
     - Call `onRateSelected(rate)` when user selects a different rate

**Reference:** See `sensor-dashboard-demo/app/src/main/java/com/isep/sensordashboard/ui/dashboard/DashboardScreen.kt` and `DetailScreen.kt`

---

### Bonus: Unit Test

**File:** Create `app/src/test/java/com/isep/sensordashboard/...`

**Task:** Add a small unit test for a helper function (e.g., computing an average from readings, or a simple filter).

**Example:**
```kotlin
@Test
fun testAverageReading() {
    val readings = listOf(
        SensorReading(SensorType.ACCELEROMETER, listOf(1f, 2f, 3f), 0L),
        SensorReading(SensorType.ACCELEROMETER, listOf(2f, 3f, 4f), 1L)
    )
    // Test average calculation
}
```

## Reference Implementation

Use the **sensor-dashboard-demo** repository as a reference for:
- Complete implementations
- Architecture patterns
- UI design
- Error handling

## Building the Project

1. Open the project in Android Studio
2. Sync Gradle files
3. The project should compile (with TODO stubs)
4. Run on a real Android device (emulators have limited sensor support)

## Notes

- All TODOs are marked with `TODO("...")` or `TODO("...")` comments
- The project structure matches the demo for easy comparison
- Keep naming consistent with the demo
- Ensure the app compiles after each TODO completion

## Submission

Complete all TODOs (UP-01 through UP-05) and ensure:
- The app compiles without errors
- Sensors are detected and displayed
- Real-time readings are shown
- Sampling rate can be changed
- UI is functional and responsive

Good luck! 🚀
