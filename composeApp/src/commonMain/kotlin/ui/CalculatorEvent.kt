package ui

import data.UnitOfMeasurement

sealed  class CalculatorEvent {
    data class InputChanged(val data: String): CalculatorEvent()
    data class OnUnitOfMeasurementChanged(val unit: UnitOfMeasurement): CalculatorEvent()
    data object ClearInput: CalculatorEvent()
}
