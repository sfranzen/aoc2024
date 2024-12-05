package day2

import getInput

val input = getInput(2)
    .map { it.split("\\s".toRegex()).map(String::toInt) }

fun isSafe(level: Collection<Int>) = level
    .windowed(2)
    .map { it[0] - it[1] }
    .run {
        all { it in 1..3 } || all { -it in 1..3 }
    }

fun isSafeWithDampening(level: Collection<Int>) =
    isSafe(level) || level.indices.map { level.filterIndexed { index, _ -> it != index } }.any(::isSafe)

fun part1() = input.count(::isSafe)

fun part2() = input.count(::isSafeWithDampening)

fun main() {
    println(part1())
    println(part2())
}