package day20

import Direction
import Map2D
import Vector2D
import getInput
import times

class RaceTrack(layout: List<String>) {
    private val map = Map2D.fromStringList(layout)
    private val start = map.positions('S').single()

    val path = buildList {
        fun next(vertex: Vector2D) = Direction.vectors.map { it + vertex }.find { position ->
            isTrack(position) && position !in this
        }

        var current: Vector2D? = start
        while (current != null) {
            add(current)
            current = next(current)
        }
    }

    fun evaluateCheats() = path.flatMapIndexed { index, position ->
        availableCheats(position).map { path.indexOf(it.second) - index - 2 }.filter { it > 0 }
    }

    fun evaluateImprovedCheats() = buildMap {
        path.forEachIndexed { index, position ->
            improvedCheats(index).forEach { cheat ->
                val length = (path[cheat.second] - position).norm
                val saved = cheat.second - index - length
                if (length in 2..20 && saved > 0) {
                    putIfAbsent(cheat, saved)
                }
            }
        }
    }.values

    private fun isTrack(position: Vector2D) = map[position]?.let { it != '#' } ?: false

    private fun isWall(position: Vector2D) = map[position] == '#'

    private fun availableCheats(position: Vector2D) =
        Direction.vectors.map { position + it to position + 2 * it }.filter { cheat ->
            isWall(cheat.first) && isTrack(cheat.second)
        }

    private fun improvedCheats(index: Int) = path.indices.drop(index).map { index to it }
}

fun part1(input: List<String>) = RaceTrack(input).evaluateCheats().count { it >= 100 }
fun part2(input: List<String>) = RaceTrack(input).evaluateImprovedCheats().count { it >= 100 }

fun main() {
    val input = getInput(20)
    println(part1(input))
    println(part2(input))
}