package utilities

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.pow


fun Int.calculatePercentage(divisor: Int): Double? {
    return if (divisor != 0) {
        String.format("%.1f", 100.0 * this.div(divisor.toDouble())).toDouble()
    } else {
        null
    }
}

fun String.convertToDouble(): Double? = this.split(" ").first().toDoubleOrNull()

fun String.stringToDate(): Date? {
    return if (this.isNotBlank()) {
        SimpleDateFormat(Constant.DATE_FORMAT).parse(this)
    } else {
        null
    }
}

fun String.converterToByte(): Long? {
    var result = ""
    for (singleChar in this) {
        result += if (singleChar.isDigit())
            singleChar
        else if (singleChar == '.' && !result.contains("."))
            singleChar
        else if (singleChar == '.' && result.contains("."))
            return null
        else break
    }
    val value = result.toDoubleOrNull()
    if (value != null) {
        return when (this.last()) {
            'K', 'k' -> (value * Constant.KILO_BYTE).toLong()
            'M', 'm' -> (value * Constant.KILO_BYTE.pow(2)).toLong()
            'G', 'g' -> (value * Constant.KILO_BYTE.pow(3)).toLong()
            else -> null
        }
    }
    return null
}