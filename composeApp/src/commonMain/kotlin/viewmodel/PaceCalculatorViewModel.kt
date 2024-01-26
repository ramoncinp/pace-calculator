package viewmodel

import data.PaceState
import data.Speeds
import data.Times
import data.UnitOfMeasurement
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import domain.speedconverter.MinPerKmConverter
import domain.speedconverter.SpeedUnitConverter
import domain.removeNonNumbers
import domain.speedconverter.KmPerHourConverter
import domain.timecalculator.TimeCalculator
import domain.toDoubleSafe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ui.CalculatorEvent

class PaceCalculatorViewModel : ViewModel() {

    private val _paceState = MutableStateFlow(PaceState())
    val paceState = _paceState.asStateFlow()

    private var converter: SpeedUnitConverter = MinPerKmConverter()

    fun onEvent(event: CalculatorEvent) {
        when (event) {
            is CalculatorEvent.InputChanged -> {
                processInput(event.data)
            }

            is CalculatorEvent.ClearInput -> {
                _paceState.update {
                    it.copy(result = "", speeds = Speeds(), speedRange = 0)
                }
            }

            is CalculatorEvent.OnUnitOfMeasurementChanged -> {
                updateConverter(event.unit)
            }
        }
    }

    private fun processInput(input: String) {
        val currentUnit = _paceState.value.selectedUnitOfMeasurement
        val result = if (currentUnit == UnitOfMeasurement.MINUTE_PER_KM) {
            processMinutePerKm(input)
        } else {
            input
        }

        converter.calculateSpeeds(result)

        val kmPerHourSpeed = _paceState.value.speeds.kmPerHour
        val times = calculateTimes(kmPerHourSpeed)
        val speedRange = calculateSpeedRange(kmPerHourSpeed.toDoubleSafe())

        _paceState.update {
            it.copy(result = result, raceTimes = times, speedRange = speedRange)
        }
    }

    private fun processMinutePerKm(minutesPerKmDisplay: String): String {
        val minutesPerKm = minutesPerKmDisplay.removeNonNumbers()
        return if (minutesPerKm.length > 4) {
            minutesPerKm
        } else {
            when (minutesPerKm.length) {
                1 -> "$minutesPerKm'"
                2 -> "${minutesPerKm[0]}'${minutesPerKm[1]}\""
                3 -> "${minutesPerKm[0]}'${minutesPerKm[1]}${minutesPerKm[2]}\""
                4 -> "${minutesPerKm[0]}${minutesPerKm[1]}'${minutesPerKm[2]}${minutesPerKm[3]}\""
                else -> ""
            }
        }
    }

    private fun updateConverter(unit: UnitOfMeasurement) {
        var willBeEnabled = true
        val newResult: String = when (unit) {
            UnitOfMeasurement.MINUTE_PER_KM -> {
                converter = MinPerKmConverter()
                _paceState.value.speeds.minPerKm
            }

            UnitOfMeasurement.KM_PER_HOUR -> {
                converter = KmPerHourConverter()
                _paceState.value.speeds.kmPerHour
            }

            UnitOfMeasurement.MILES_PER_HOUR -> {
                willBeEnabled = false
                _paceState.value.speeds.miPerHour
            }

            UnitOfMeasurement.MINUTE_PER_MILE -> {
                willBeEnabled = false
                _paceState.value.speeds.minPerMi
            }
        }

        _paceState.update {
            it.copy(
                result = newResult,
                inputEnabled = willBeEnabled,
                selectedUnitOfMeasurement = unit
            )
        }
    }

    private fun SpeedUnitConverter.calculateSpeeds(data: String) {
        val minPerKm = toMinPerKm(data)
        val kmPerHour = toKmPerHour(data)
        val miPerHour = toMiPerHour(data)
        val minPerMi = toMinPerMi(data)

        _paceState.update {
            it.copy(
                speeds = Speeds(
                    kmPerHour = kmPerHour,
                    minPerKm = minPerKm,
                    miPerHour = miPerHour,
                    minPerMi = minPerMi
                )
            )
        }
    }

    private fun calculateTimes(speed: String): Times {
        val timesCalculator = TimeCalculator(speed.toDoubleSafe())
        return Times(
            fiveKm = timesCalculator.calculateFiveKmTime(),
            tenKm = timesCalculator.calculateTenKmTime(),
            halfMarathon = timesCalculator.calculateHalfMarathonTime(),
            marathon = timesCalculator.calculateMarathonTime()
        )
    }

    private fun calculateSpeedRange(speed: Double): Int {
        return if (speed in 0.7..7.0) {
            1
        } else if (speed > 7.0 && speed <= 14.3) {
            2
        } else if (speed > 7.0 && speed <= 14.3) {
            3
        } else if (speed > 14.3 && speed <= 19.7) {
            4
        } else if (speed > 19.7 && speed <= 24.9) {
            5
        } else if (speed > 25.0) {
            6
        } else {
            0
        }
    }
}
