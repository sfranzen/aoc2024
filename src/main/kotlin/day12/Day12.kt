package day12

import Direction
import Map2D
import Vector2D
import buildGraph
import getInput

data class Plant(val position: Vector2D, val value: Char) {
    constructor(row: Int, col: Int, plant: Char) : this(Vector2D(row, col), plant)
}

typealias Plot = List<Plant>

class GardenPlots(input: List<String>) {
    private val map = Map2D.fromStringList(input)

    private val garden = buildGraph<Plant> {
        map.mapIndexed(::Plant).flatten().forEach(::addVertex)

        vertices.forEach { plant ->
            Direction.entries.map { it.vector + plant.position }
                .mapNotNull { p -> vertices.find { it.position == p && it.value == plant.value } }
                .forEach { addEdge(plant, it) }
        }
    }

    // Find the plots as "connected components" of the above graph, through a recursive DFS
    val plots = buildList {
        val visited = mutableListOf<Plant>()
        fun findPlot(plant: Plant): Plot {
            if (plant in visited) return emptyList()
            visited.add(plant)
            return buildList {
                add(plant)
                garden[plant]?.flatMap(::findPlot)?.let(::addAll)
            }
        }
        garden.vertices.map(::findPlot).filter { it.isNotEmpty() }.let(::addAll)
    }

    val price = plots.sumOf(::fencePrice)

    // The perimeter length is determined by the adjacency list of each vertex:
    // a plant with no neighbours needs 4 fences, 1 neighbour needs 3 fences, etc.
    fun perimeter(plot: Plot) = plot.sumOf { 4 - (garden.neighbours(it)?.size ?: 0) }

    fun fencePrice(plot: Plot) = plot.size * perimeter(plot)
}

fun part1(input: List<String>) = GardenPlots(input).price

fun main() {
    val input = getInput(12)
    println(part1(input))
}