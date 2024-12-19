package day14

import getInput
import java.lang.Math.floorMod

data class Vector(val x: Int, val y: Int)

data class Robot(var position: Vector, val velocity: Vector)

class RoboMap(private val robots: List<Robot>, private val width: Int, private val height: Int) {
    val safetyFactor: ULong
        get() = robots.groupBy { quadrant(it.position) }.filterNot { it.key == null }.map { it.value.size.toULong() }
            .reduce(ULong::times)

    fun step() = robots.forEach(::update)

    override fun toString(): String = buildString {
        for (y in 0..<height) {
            for (x in 0..<width) {
                val count = robots.count { it.position.x == x && it.position.y == y }
                val char = when {
                    count == 0 -> '.'
                    count > 9 -> '*'
                    else -> count.toString()
                }
                append(char)
            }
            append(System.lineSeparator())
        }
    }.trimEnd()

    private fun update(robot: Robot) = with(robot) {
        position = position.let { Vector(floorMod(it.x + velocity.x, width), floorMod(it.y + velocity.y, height)) }
    }

    private fun quadrant(position: Vector): Int? = with(position) {
        val midX = width / 2
        val midY = height / 2
        when {
            x == midX || y == midY -> null
            x < midX && y < midY -> 0
            y < midY -> 1
            x < midX -> 2
            else -> 3
        }
    }
}

fun createRoboMap(input: List<String>, width: Int, height: Int) = input.map { string ->
    val (px, py, vx, vy) = "-?(\\d+)".toRegex().findAll(string).map {
        val sign = if (it.value.startsWith('-')) -1 else 1
        sign * it.groupValues[1].toInt()
    }.toList()
    Robot(Vector(px, py), Vector(vx, vy))
}.let { RoboMap(it, width, height) }

fun part1(input: List<String>, width: Int, height: Int): ULong {
    val map = createRoboMap(input, width, height)
    repeat(100) { map.step() }
    return map.safetyFactor
}

fun part2(input: List<String>) = 0

fun main() {
    val input = getInput(14)
    println(part1(input, 101, 103))
}