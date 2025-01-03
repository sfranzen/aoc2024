package day8

import Map2D
import Vector2D
import combinations
import getInput
import line

data class Antenna(val position: Vector2D, val frequency: Char)

class AntennaMap(layout: List<String>) : Map2D<Char>(fromStringList(layout)) {
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
        buildList {
            addAll(line(a.position, -dist).takeWhile { this@AntennaMap.contains(it) })
            addAll(line(b.position, dist).takeWhile { this@AntennaMap.contains(it) })
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