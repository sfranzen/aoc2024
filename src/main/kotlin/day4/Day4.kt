package day4

import getInput

val input = getInput(4)
val xmas = arrayOf("XMAS", "SAMX")
val x_mas = arrayOf("M.S.A.M.S", "S.S.A.M.M", "S.M.A.S.M", "M.M.A.S.S").map(String::toRegex)

fun countXmas(input: String) = if (input.length < 4) 0 else input.windowed(4).count { it in xmas }

fun part1(input: List<String>): Int {
    if (input.isEmpty()) return 0

    val height = input.size
    val width = input[0].length
    var total = input.sumOf(::countXmas)

    total += input.indices.map { i -> input.map { line -> line[i] }.joinToString("") }.sumOf(::countXmas)

    // down diagonals
    for (i in 0..<height) {
        total += buildString {
            for (j in 0..<width - i) {
                append(input[i + j][j])
            }
        }.run(::countXmas)
    }

    // down diagonals
    for (j in 1..<width) {
        total += buildString {
            for (i in 0..<height - j) {
                append(input[i][i + j])
            }
        }.run(::countXmas)
    }

    // up
    for (i in height - 1 downTo 0) {
        total += buildString {
            for (j in 0..i) {
                append(input[i - j][j])
            }
        }.run(::countXmas)
    }

    // up
    for (j in 1..width) {
        total += buildString {
            for (i in height - 1 downTo j) {
                append(input[i][height - 1 - i + j])
            }
        }.run(::countXmas)
    }
    return total
}

fun part2(input: List<String>): Int = input.windowed(3).sumOf { lines ->
    val top = lines[0]

    // join and test the 3*3 characters starting at the given index on each line
    List(top.windowed(3).size) { row ->
        lines.joinToString("") { it.substring(row, row + 3) }
    }.count { str -> x_mas.any { it.matches(str) } }
}

fun main() {
    println(part1(input))
    println(part2(input))
}