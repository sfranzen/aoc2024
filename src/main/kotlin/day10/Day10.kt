package day10

import DirectedGraph
import Vector2D
import buildDiGraph
import buildGraph
import getInput

enum class Direction(val vector: Vector2D) {
    Up(-1, 0), Right(0, 1), Down(1, 0), Left(0, -1);

    constructor(row: Int, col: Int) : this(Vector2D(row, col))
}

data class Location(val position: Vector2D, val height: Int) {
    constructor(row: Int, col: Int, height: Int) : this(Vector2D(row, col), height)
}

class TrailMap(input: List<List<Int>>) {
    val map = buildGraph {
        input.forEachIndexed { rowIndex, row ->
            row.mapIndexed { colIndex, height -> Location(rowIndex, colIndex, height) }.forEach(::addVertex)
        }
        vertices.forEach { vertex ->
            directNeighbours(vertex).mapNotNull { position -> vertices.find { it.position == position } }.forEach {
                addEdge(vertex, it)
            }
        }
    }

    val trailHeads = map.vertices.filter { it.height == 0 }.toSet()

    val graph = buildDiGraph(map.vertices) {
        fun populate(position: Location) {
            connectedNeighbours(position)?.forEach {
                addEdge(position, it)
                populate(it)
            }
        }
        trailHeads.forEach(::populate)
    }

    val score = trailHeads.sumOf { leafNodes(it).distinct().size }

    private fun connectedNeighbours(loc: Location) = map[loc]?.filter { it.height == loc.height + 1 }

    private fun leafNodes(loc: Location): List<Location> = when {
        !map.contains(loc) -> emptyList()
        loc.height == 9 -> listOf(loc)
        else -> graph[loc]?.flatMap(::leafNodes) ?: emptyList()
    }

    companion object {
        fun fromStringList(list: List<String>) = TrailMap(list.map { it.map { char -> char.digitToIntOrNull() ?: -1 } })
        private fun directNeighbours(loc: Location) = Direction.entries.map { loc.position + it.vector }
    }
}

fun part1(input: List<String>) = TrailMap.fromStringList(input).score
fun part2(input: List<String>) = 0

fun main() {
    val input = getInput(10)
    println(part1(input))
}