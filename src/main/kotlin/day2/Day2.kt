package day2

import getInput

val input = getInput(2)

fun isSafe(level: Collection<Int>) =
    level.windowed(2)
        .map { it[0] - it[1] }
        .run {
            all { it in 1..3 } || all { -it in 1..3 }
        }

fun part1() =
    input.readLines()
        .map { it.split("\\s".toRegex()).map(String::toInt) }
        .count(::isSafe)

fun main() {
    println(part1())
}