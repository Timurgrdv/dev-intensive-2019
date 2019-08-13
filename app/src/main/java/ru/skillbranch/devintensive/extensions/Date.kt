package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.abs

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

    fun plural(value: Int): String {
        return "$value ${getPluralForm(value, this)}"
    }
}

fun getPluralForm(amount: Int, units: TimeUnits): String {
    val posAmount = abs(amount) % 100

    return when (posAmount) {
        1 -> Counter.ONE.get(units)
        in 2..4 -> Counter.FEW.get(units)
        0, in 5..19 -> Counter.MANY.get(units)
        else -> getPluralForm(posAmount % 10, units)
    }
}

enum class Counter(
    private val second: String,
    private val minute: String,
    private val hour: String,
    private val day: String
) {
    ONE("секунду", "минуту", "час", "день"),
    FEW("секунды", "минуты", "часа", "дня"),
    MANY("секунд", "минут", "часов", "дней");

    fun get(unit: TimeUnits): String {
        return when (unit) {
            TimeUnits.SECOND -> second
            TimeUnits.MINUTE -> minute
            TimeUnits.HOUR -> hour
            TimeUnits.DAY -> day
        }
    }

}





