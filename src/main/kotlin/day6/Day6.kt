package day6

import day6.Direction.*
import getInput

class Room(private val layout: List<String>) {
    private val guard = findGuard(layout)

    fun patrolRoute() = pathFrom(guard)

    fun pathFrom(guard: Guard) = generateSequence(guard) {
        val nextPos = it.nextPosition()
        when (inspect(nextPos)) {
            null -> null
            '#' -> it.copy(direction = it.direction.next)
            else -> it.copy(position = nextPos)
        }
    }

    fun inspect(pos: Position) = with(pos) { layout.getOrNull(row)?.getOrNull(col) }

    fun showPath(path: Collection<Guard>): String {
        val clone = layout.map(String::toMutableList)
        for (guard in path) {
            with(guard.position) {
                clone[row][col] = guard.direction.symbol
            }
        }
        return clone.joinToString("\n") { it.joinToString("") }
    }
}

data class Guard(val position: Position, val direction: Direction) {
    fun nextPosition() = nextPosition(position, direction)
}

data class Position(val row: Int, val col: Int)

enum class Direction(val symbol: Char) {
    Up('^'), Right('>'), Down('v'), Left('<');

    val next get() = entries[(ordinal + 1) % entries.size]
}

fun nextPosition(position: Position, direction: Direction) = with(position) {
    when (direction) {
        Up -> copy(row = row - 1)
        Right -> copy(col = col + 1)
        Down -> copy(row = row + 1)
        Left -> copy(col = col - 1)
    }
}

fun findGuard(layout: List<String>): Guard = layout.flatMapIndexed { rowIndex, row ->
    row.mapIndexedNotNull { colIndex, symbol ->
        Direction.entries.find { it.symbol == symbol }?.let { Guard(Position(rowIndex, colIndex), it) }
    }
}.single()

fun part1(input: List<String>) = Room(input).patrolRoute().distinctBy(Guard::position).count()

fun computeCycles(room: Room): Sequence<Set<Guard>> {
    val route = room.patrolRoute()
    return route.filterNot { guard ->
        guard.nextPosition().let { pos -> pos == route.first().position || room.inspect(pos).let { it == '#' || it == null } }
    }.mapNotNull { guard ->
        val direction = guard.direction.next
        val start = guard.copy(direction = direction)
        val history = mutableSetOf(guard)
        room.pathFrom(start).map(history::add).any { !it }.let { if (it) history else null }
    }
}

fun part2(input: List<String>) = Room(input).let(::computeCycles).distinctBy { it.first() }.count()

fun main() {
    val input = getInput(6)
    println(part1(input))
    println(part2(input))
}