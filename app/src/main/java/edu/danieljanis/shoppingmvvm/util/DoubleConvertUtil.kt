package edu.danieljanis.shoppingmvvm.util

object DoubleConvertUtil {
    fun convertDouble(value: Double): String {
        return String.format("%,.2f", value)
    }
}
