package day6

import getInput

class Room(private val layout: List<String>) {
    private val guard = findGuard(layout)
    private val visited = mutableSetOf(guard.position)
    private var finished = false

    fun predict(): Set<Position> {
        do update()
        while (!finished)
        return visited
    }

    private fun update() {
        if (finished) return

        val nextPos = guard.nextPosition()
        when (inspect(nextPos)) {
            null -> finished = true
            '#' -> guard.turn()
            else -> {
                guard.position = nextPos
                visited.add(nextPos)
            }
        }
    }

    private fun inspect(pos: Position) = with(pos) { layout.getOrNull(row)?.getOrNull(col) }
}

data class Guard(var position: Position, var direction: Direction) {
    fun nextPosition() = with(position) {
        when (direction) {
            Direction.Up -> Position(row - 1, col)
            Direction.Right -> Position(row, col + 1)
            Direction.Down -> Position(row + 1, col)
            Direction.Left -> Position(row, col - 1)
        }
    }

    fun turn() {
        direction = direction.run { Direction.entries[(ordinal + 1) % Direction.entries.size] }
    }
}

data class Position(val row: Int, val col: Int)

enum class Direction(val symbol: Char) {
    Up('^'), Right('>'), Down('v'), Left('<')
}

fun findGuard(layout: List<String>): Guard = layout.flatMapIndexed { rowIndex, row ->
    row.mapIndexedNotNull { colIndex, symbol ->
        Direction.entries.find { it.symbol == symbol }?.let { Guard(Position(rowIndex, colIndex), it) }
    }
}.single()

fun part1(input: List<String>) = Room(input).predict().size

fun main() {
    val input = getInput(6)
    println(part1(input))
}