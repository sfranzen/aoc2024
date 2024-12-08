package day6

import day6.Direction.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class Day6Test {
    private val testInput = """
        ....#.....
        .........#
        ..........
        ..#.......
        .......#..
        ..........
        .#..^.....
        ........#.
        #.........
        ......#...
    """.trimIndent().split('\n')

    @Test
    fun `Direction_next yields the next direction`() {
        val input = Direction.entries

        assertAll(
            input.map {
                val expected = when (it) {
                    Up -> Right
                    Right -> Down
                    Down -> Left
                    Left -> Up
                }
                { assertEquals(expected, it.next) }
            })
    }

    @Test
    fun `findGuard() returns the single guard when present`() {
        val expected = Guard(Position(6, 4), Up)
        assertEquals(expected, findGuard(testInput))
    }

    @Test
    fun `findGuard() throws when no guard is present`() {
        assertThrows<NoSuchElementException> { findGuard(listOf("")) }
    }

    @Test
    fun `findGuard() throws when more than one guard is present`() {
        assertThrows<IllegalArgumentException> { findGuard(listOf("^^")) }
    }

    @Test
    fun part1() {
        assertEquals(41, part1(testInput))
    }

    @Test
    fun part2() {
        assertEquals(6, part2(testInput))
    }
}