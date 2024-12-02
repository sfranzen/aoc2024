package day1

import getInput
import kotlin.math.abs

val input = getInput(1)

fun getLists() = input.readLines()
    .map { line ->
        line.split("\\s+".toRegex())
            .map(String::toInt)
            .let{ it[0] to it[1] }
    }
    .unzip()

fun part1(): Int = getLists().run {
    first.sorted().zip(second.sorted()).sumOf { abs(it.first - it.second) }
}

fun part2(): Int = getLists().run {
    first.sumOf { left -> left * second.count { right -> left == right } }
}

fun main() {
    println(part1())
    println(part2())
}