package day15

import Direction
import Map2D
import Vector2D
import getInput
import line

class Warehouse(
    private var robotPos: Vector2D, private val walls: List<Vector2D>, private val boxes: MutableList<Vector2D>
) {
    val coordinateSum get() = boxes.sumOf { 100 * it.row + it.col }

    fun run(instructions: List<Direction>) = instructions.forEach(::moveRobot)

    fun moveRobot(direction: Direction) {
        val nextPos = robotPos + direction.vector
        when (nextPos) {
            in walls -> return
            in boxes -> {
                val positions = line(nextPos, direction.vector).takeWhile { it !in walls }.toList()
                val boxPositions = positions.takeWhile { it in boxes }
                if (boxPositions.size == positions.size) return
                boxes.remove(boxPositions[0])
                boxes.add(positions[boxPositions.size])
            }
        }
        robotPos = nextPos
    }
}

private fun direction(input: Char) = when (input) {
    '^' -> Direction.Up
    '>' -> Direction.Right
    'v' -> Direction.Down
    '<' -> Direction.Left
    else -> null
}

fun createWarehouse(input: List<String>): Pair<Warehouse, List<Direction>> =
    input.partition { it.startsWith('#') }.let { (state, moves) ->
        val map = Map2D.fromStringList(state)
        val robot = map.positionOf('@') ?: throw IllegalArgumentException("Initial state must contain a robot.")
        val walls = map.positions('#')
        val boxes = map.positions('O')
        Warehouse(robot, walls, boxes.toMutableList()) to moves.joinToString("").trim().mapNotNull(::direction)
    }

fun part1(input: List<String>) = createWarehouse(input).let { (warehouse, moves) ->
    warehouse.run(moves)
    warehouse.coordinateSum
}

fun part2(input: List<String>) = 0

fun main() {
    val input = getInput(15)
    println(part1(input))
}