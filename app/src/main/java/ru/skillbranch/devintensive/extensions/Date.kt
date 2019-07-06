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
    val minutesEndings : Array<String> = arrayOf(" минут", " минуту", " минуты")
    val hoursEndings : Array<String> = arrayOf(" часов", " час", " часа")
    val daysEndings : Array<String> = arrayOf(" дней", " день", " дня")
    val dateDiff = date.time - this.time
    val absDateDiff = if (dateDiff < 0) -dateDiff else dateDiff
    return when (dateDiff) {
        in -45*1000L .. -1001L -> "через несколько секунд"
        in -75*1000L .. -45*1000L+1 -> "через минуту"
        in -45*60*1000L .. -75*1000L+1 -> "через " + absDateDiff/1000/60 + minutesEndings[getPluralIndex((absDateDiff/1000/60%100).toInt())]
        in -75*60*1000L .. -45*60*1000L+1 -> "через час"
        in -22*60*60*1000L .. -75*60*1000L+1 ->  "через " + absDateDiff/1000/60/60 + hoursEndings[getPluralIndex((absDateDiff/1000/60/60%100).toInt())]
        in -26*60*60*1000L .. -22*60*60*1000L+1 -> "через день"
        in -360*60*60*1000L .. -26*60*60*1000L+1 -> "через " + absDateDiff/1000/60/60/24 + daysEndings[getPluralIndex((absDateDiff/1000/60/60/24%100).toInt())]
        in -1000L .. 1000L  -> "только что"
        in 1001L .. 45*1000L -> "несколько секунд назад"
        in 45*1000L+1 .. 75*1000L -> "минуту назад"
        in 75*1000L+1 .. 45*60*1000L -> "" + absDateDiff/1000/60 +minutesEndings[getPluralIndex((absDateDiff/1000/60%100).toInt())] + " назад"
        in 45*60*1000L+1 .. 75*60*1000L -> "час назад"
        in 75*60*1000L+1 .. 22*60*60*1000L ->  "" + absDateDiff/1000/60/60 + hoursEndings[getPluralIndex((absDateDiff/1000/60/60%100).toInt())] + " назад"
        in 22*60*60*1000L+1 .. 26*60*60*1000L -> "день назад"
        in 26*60*60*1000L+1 .. 360*60*60*1000L -> "" + absDateDiff/1000/60/60/24 + daysEndings[getPluralIndex((absDateDiff/1000/60/60/24%100).toInt())] + " назад"
        else -> if (dateDiff < 0) "более чем через год" else "более года назад"
    }
}

private fun getPluralIndex(value: Int): Int {
    return when (val i = (value % 100)) {
        in 11..14 -> 0
        else -> {   when (i % 10) {
            in 1..1 -> 1
            in 2..4 -> 2
            else -> 0
                    }
                }
    }
}

interface Plural {
    fun plural(value: Int): String
}

enum class TimeUnits : Plural {
    SECOND {
        override fun plural(value: Int): String {
            val secundsEndings : Array<String> = arrayOf(" секунд", " секунду", " секунды")
            return value.toString() + secundsEndings[getPluralIndex(value)]
        }
    },
    MINUTE {
        override fun plural(value: Int): String {
            val minutesEndings : Array<String> = arrayOf(" минут", " минуту", " минуты")
            return value.toString() + minutesEndings[getPluralIndex(value)]
        }
    },
    HOUR {
        override fun plural(value: Int): String {
            val hoursEndings : Array<String> = arrayOf(" часов", " час", " часа")
            return value.toString() + hoursEndings[getPluralIndex(value)]

        }
    },
    DAY {
        override fun plural(value: Int): String {
            val daysEndings : Array<String> = arrayOf(" дней", " день", " дня")
            return value.toString() + daysEndings[getPluralIndex(value)]
        }
    }

}
