package day13

import Vector2D
import getInput

typealias Button = Vector2D

data class ClawMachine(val buttons: List<Button>, val prizePosition: Vector2D, val improvedPrecision: Boolean = false) {
    private val correction: Long = if (improvedPrecision) 10000000000000 else 0

    // The smallest number of tokens that can get us to the position of the prize. That is, solve the equation
    // m*A + n*B = P, which is a linear algebra exercise.
    fun minimumCost(): Long? {
        val (a, b) = buttons
        val (px, py) = prizePosition.run { row.toLong() + correction to col.toLong() + correction }
        val n = (a.row.toLong() * py - a.col.toLong() * px) / (a.row * b.col - a.col * b.row).toLong()
        val m = (px - n * b.row.toLong()) / a.row.toLong()
        return when {
            !improvedPrecision && (m !in 0..100 || n !in 0..100) -> null
            m * a.row.toLong() + n * b.row.toLong() == px && m * a.col.toLong() + n * b.col.toLong() == py -> 3 * m + n
            else -> null
        }
    }
}

fun createMachines(input: List<String>, improvedPrecision: Boolean = false): List<ClawMachine> =
    input.filterNot(String::isBlank).chunked(3).map { inputs ->
        fun String.parseVectors() =
            "\\d+".toRegex().findAll(this).map { it.value.toInt() }.toList().let { (x, y) -> Vector2D(x, y) }

        val buttons = inputs.slice(0..1).map(String::parseVectors)
        val prize = inputs.last().parseVectors()
        ClawMachine(buttons, prize, improvedPrecision)
    }

fun part1(input: List<String>) = createMachines(input).mapNotNull { it.minimumCost() }.sum()
fun part2(input: List<String>) = createMachines(input, true).mapNotNull { it.minimumCost() }.sum()

fun main() {
    val input = getInput(13)
    println(part1(input))
    println(part2(input))
}