package day10

import Map
import Vector2D

class HeightMap(input: List<List<Int>>) : Map<Int>(input) {
    val trailHeads = mapIndexed { row, col, height ->
        when {
            height == 0 -> Vector2D(row, col)
            else -> null
        }
    }.flatten().filterNotNull()

    companion object {
        fun fromStringList(list: List<String>) = HeightMap(list.map { it.map(Char::digitToInt) })
    }
}

fun part1(input: String) = 0
fun part2(input: String) = 0