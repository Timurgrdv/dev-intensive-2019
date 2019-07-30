package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time

    time += when (units) {  // будет принимать единицу измерения "units"
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    val milliseconds = Date().time - date.time
    val seconds = milliseconds / SECOND
    val minutes = seconds / MINUTE
    val hours = minutes / HOUR
    val days = hours / DAY

    return "$seconds"

}

/** добавим класс перечислений*/
enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(number: Int): String {

        val strNumber = number.toString()
        val lastSymbol: String = (strNumber[strNumber.length - 1]).toString()

        when (this) {
            TimeUnits.SECOND -> {
                if (strNumber != "10" && strNumber != "11" && strNumber != "12" && strNumber != "13" && strNumber != "14" && strNumber != "15"
                    && strNumber != "16" && strNumber != "17" && strNumber != "18" && strNumber != "19"
                ) {
                    when (lastSymbol) {
                        "1" -> return "$strNumber секунду"
                        "2", "3", "4" -> return "$strNumber секунды"
                        "5", "6", "7", "8", "9", "0" -> return "$strNumber секунд"
                        else -> return "$strNumber секунд"
                    }
                } else
                    return "$strNumber минут"
            }
            TimeUnits.MINUTE -> {
                if (strNumber != "10" && strNumber != "11" && strNumber != "12" && strNumber != "13" && strNumber != "14" && strNumber != "15"
                    && strNumber != "16" && strNumber != "17" && strNumber != "18" && strNumber != "19"
                ) {
                    when (lastSymbol) {
                        "1" -> return "$strNumber минуту"
                        "2", "3", "4" -> return "$strNumber минуты"
                        "5", "6", "7", "8", "9", "0" -> return "$strNumber минут"
                        else -> return "$strNumber минут"
                    }
                } else
                    return "$strNumber минут"
            }
            TimeUnits.HOUR -> {
                if (strNumber != "10" && strNumber != "11" && strNumber != "12" && strNumber != "13" && strNumber != "14" && strNumber != "15"
                    && strNumber != "16" && strNumber != "17" && strNumber != "18" && strNumber != "19"
                ) {
                    when (lastSymbol) {
                        "1" -> return "$strNumber час"
                        "2", "3", "4" -> return "$strNumber часа"
                        "5", "6", "7", "8", "9", "0" -> return "$strNumber часов"
                        else -> return "$strNumber часов"
                    }
                } else
                    return "$strNumber часов"
            }
            TimeUnits.DAY -> {
                if (strNumber != "10" && strNumber != "11" && strNumber != "12" && strNumber != "13" && strNumber != "14" && strNumber != "15"
                    && strNumber != "16" && strNumber != "17" && strNumber != "18" && strNumber != "19"
                ) {
                    when (lastSymbol) {
                        "1" -> return "$strNumber день"
                        "2", "3", "4" -> return "$strNumber дня"
                        "5", "6", "7", "8", "9", "0" -> return "$strNumber дней"
                        else -> return "$strNumber дней"
                    }
                } else
                    return "$strNumber дней"
            }
        }

        return ""
    }
}


