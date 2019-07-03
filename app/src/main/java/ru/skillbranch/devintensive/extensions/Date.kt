package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = SECOND * 60
const val HOUR = MINUTE * 60
const val DAY = HOUR * 24

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits): Date {
    var time = this.time
    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    val minutesEndings : Array<String> = arrayOf(" минут", " минута", " минуты")
    val hoursEndings : Array<String> = arrayOf(" часов", " час", " часа")
    val daysEndings : Array<String> = arrayOf(" дней", " день", " дня")
    val dateDiff = date.time - this.time
    val absDateDiff = if (dateDiff < 0) -dateDiff else dateDiff
    return when (dateDiff) {
        in -45*1000L .. -1001L -> "через несколько секунд"
        in -75*1000L .. -45*1000L+1 -> "через минуту"
        in -45*60*1000L .. -75*1000L+1 -> "через " + absDateDiff/1000/60 +minutesEndings[endingIndex(absDateDiff/1000/60)]
        in -75*60*1000L .. -45*60*1000L+1 -> "через час"
        in -22*60*60*1000L .. -75*60*1000L+1 ->  "через " + absDateDiff/1000/60/60 + hoursEndings[endingIndex(absDateDiff/1000/60/60)]
        in -26*60*60*1000L .. -22*60*60*1000L+1 -> "через день"
        in -360*60*60*1000L .. -26*60*60*1000L+1 -> "через " + absDateDiff/1000/60/60/24 + daysEndings[endingIndex(absDateDiff/1000/60/60/24)]
        in -1000L .. 1000L  -> "только что"
        in 1001L .. 45*1000L -> "несколько секунд назад"
        in 45*1000L+1 .. 75*1000L -> "минуту назад"
        in 75*1000L+1 .. 45*60*1000L -> "" + absDateDiff/1000/60 +minutesEndings[endingIndex(absDateDiff/1000/60)] + " назад"
        in 45*60*1000L+1 .. 75*60*1000L -> "час назад"
        in 75*60*1000L+1 .. 22*60*60*1000L ->  "" + absDateDiff/1000/60/60 + hoursEndings[endingIndex(absDateDiff/1000/60/60)] + " назад"
        in 22*60*60*1000L+1 .. 26*60*60*1000L -> "день назад"
        in 26*60*60*1000L+1 .. 360*60*60*1000L -> "" + absDateDiff/1000/60/60/24 + daysEndings[endingIndex(absDateDiff/1000/60/60/24)] + " назад"
        else -> if (dateDiff < 0) "более чем через год" else "более года назад"
    }
}

private fun endingIndex(diff: Long): Int {
    return when (val i = (diff % 100).toInt()) {
        in 11..14 -> 0
        else -> {   when (i % 10) {
                        in 1..1 -> 1
                        in 2..4 -> 2
                        else -> 0
                    }
                }
    }
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}
