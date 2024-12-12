package day10

import DirectedGraph
import Map
import Vector2D
import getInput

enum class Direction(val vector: Vector2D) {
    Up(-1, 0), Right(0, 1), Down(1, 0), Left(0, -1);

    constructor(row: Int, col: Int) : this(Vector2D(row, col))
}

class TrailMap(input: List<List<Int>>) : Map<Int>(input) {
    val trailHeads = mapIndexed { row, col, height ->
        when (height) {
            0 -> Vector2D(row, col)
            else -> null
        }
    }.flatten().filterNotNull()

    val graph = DirectedGraph(trailHeads).apply {
        vertices.forEach { populate(it) }
    }

    val score = trailHeads.sumOf { leafNodes(it).distinct().size }

    private fun DirectedGraph<Vector2D>.populate(position: Vector2D) {
        connectedNeighbours(position).forEach {
            addVertex(it)
            addEdge(position, it)
            populate(it)
        }
    }

    private fun connectedNeighbours(position: Vector2D) = buildList {
        get(position)?.let { value -> addAll(neighbours(position).filter { get(it) == value + 1 }) }
    }

    private fun neighbours(position: Vector2D) = Direction.entries.map { position + it.vector }

    private fun leafNodes(position: Vector2D): List<Vector2D> = when (get(position)) {
        null -> emptyList()
        9 -> listOf(position)
        else -> graph[position]?.flatMap(::leafNodes) ?: emptyList()
    }

    companion object {
        fun fromStringList(list: List<String>) =
            TrailMap(list.map { it.map { char -> char.digitToIntOrNull() ?: -1 } })
    }
}

fun part1(input: List<String>) = TrailMap.fromStringList(input).score
fun part2(input: List<String>) = 0

fun main() {
    val input = getInput(10)
    println(part1(input))
}