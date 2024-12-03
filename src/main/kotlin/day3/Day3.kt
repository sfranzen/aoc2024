package day3

import getInput

val input = getInput(3)

fun String.trySplit(delimiter: String) = split(delimiter, limit = 2).let { it[0] to it.getOrElse(1) { "" } }

fun part1(input: String): UInt = """mul\((\d+),(\d+)\)""".toRegex().findAll(input).sumOf {
    it.groupValues.drop(1).map(String::toUInt).reduce(UInt::times)
}

fun part2(input: String, enabled: Boolean = true): UInt {
    val activate = "do()"
    val deactivate = "don't()"

    if (input.isBlank()) return 0u

    if (enabled) {
        val (head, tail) = input.trySplit(deactivate)
        return part1(head) + part2(tail, false)
    }

    val (_, tail) = input.trySplit(activate)
    return part2(tail)
}

fun main() {
    println(input.readLines().sumOf(::part1))
    println(input.readText().let(::part2))
}