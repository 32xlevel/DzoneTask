package me.i32xlevel.dzonetask.extensions

import java.util.*

// "yyyy-MM-dd" or "dd-MM-yyyy" --> "dd.MM.yyyy"
fun String.format(): String {
    if (this.isBlank()) return this

    val (year, month, day) = this.getOptions()

    return "${day}.${month}.${year}"
}

// "yyyy-MM-dd" or "dd-MM-yyyy"
fun String.toAge(): String {
    if (this.isBlank()) return this

    val (year, month, day) = this.getOptions()

    return getAgeFromBirthday(year, month, day)
}

// year, month, day
private fun String.getOptions(): Triple<Int, Int, Int> {
    val options = this.split("-")

    return if (options[0].length == 4) {
        val year = options[0].toInt()
        val month = options[1].toInt()
        val day = options[2].toInt()

        Triple(year, month, day)
    } else {
        val year = options[2].toInt()
        val month = options[1].toInt()
        val day = options[0].toInt()

        Triple(year, month, day)
    }
}

private fun getAgeFromBirthday(year: Int, month: Int, day: Int): String {
    val today = Calendar.getInstance()
    val dob = Calendar.getInstance().apply { set(year, month, day) }

    var age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR)
    if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) age--

    return age.toString()
}