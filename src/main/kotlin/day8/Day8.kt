package day8

import Map
import Vector2D
import combinations
import getInput

data class Antenna(val position: Vector2D, val frequency: Char)

class AntennaMap(layout: List<String>) : Map<Char>(fromStringList(layout)) {
    val antennas = mapIndexed { row, col, ch ->
        when (ch) {
            '.' -> null
            else -> Antenna(Vector2D(row, col), ch)
        }
    }.flatten().filterNotNull()

    val combinations = antennas.combinations(2).filter { (a, b) -> a.frequency == b.frequency }

    val antinodes = combinations.flatMap { (a, b) ->
        val dist = b.position - a.position
        listOf(a.position - dist, b.position + dist)
    }.filter(::contains).distinct()

    val updatedAntinodes = combinations.flatMap { (a, b) ->
        val dist = b.position - a.position
        fun line(position: Vector2D, direction: Vector2D) =
            generateSequence(position) { it + direction }.takeWhile(::contains)
        buildList {
            addAll(line(a.position, -dist))
            addAll(line(b.position, dist))
        }
    }.distinct()
}

fun part1(input: List<String>) = AntennaMap(input).antinodes.count()
fun part2(input: List<String>) = AntennaMap(input).updatedAntinodes.count()

fun main() {
    val input = getInput(8)
    println(part1(input))
    println(part2(input))
}