package dev.vrba.dubsbot

fun String.dubsNumber(): Int {
    val reversed = this.reversed()
    val first = reversed.first()

    return reversed.takeWhile { it == first }.count()
}

fun Long.dubsNumber(): Int = this.toString().dubsNumber()
