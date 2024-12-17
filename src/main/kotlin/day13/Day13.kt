package day13

import Vector2D
import getInput

typealias Button = Vector2D

data class ClawMachine(val buttons: List<Button>, val prizePosition: Vector2D) {
    // The smallest number of tokens that can get us to the position of the prize. That is, solve the equation
    // m*A + n*B = P, which is a linear algebra exercise.
    fun minimumCost(): Int? {
        val (a, b) = buttons
        val p = prizePosition
        val n = (a.row * p.col - a.col * p.row) / (a.row * b.col - a.col * b.row)
        val m = (p.row - n * b.row) / a.row
        return when {
            m !in 0..100 || n !in 0..100 -> null
            m * a.row + n * b.row == p.row && m * a.col + n * b.col == p.col -> 3 * m + n
            else -> null
        }
    }
}

fun createMachines(input: List<String>): List<ClawMachine> = input.filterNot(String::isBlank).chunked(3).map { inputs ->
    fun String.parseVectors() =
        "\\d+".toRegex().findAll(this).map { it.value.toInt() }.toList().let { (x, y) -> Vector2D(x, y) }

    val buttons = inputs.slice(0..1).map(String::parseVectors)
    val prize = inputs.last().parseVectors()
    ClawMachine(buttons, prize)
}

fun part1(input: List<String>) = createMachines(input).mapNotNull { it.minimumCost() }.sum()
fun part2(input: List<String>) = 0

fun main() {
    val input = getInput(13)
    println(part1(input))
}