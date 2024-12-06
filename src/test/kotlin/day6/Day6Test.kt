package day6

import org.junit.jupiter.api.Test
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
    fun `turn() turns the guard to the right`() {
        val sut = Guard(Position(0, 0), Direction.Left)
        val expected = Direction.entries

        expected.forEach {
            sut.turn()
            assertEquals(it, sut.direction)
        }
    }

    @Test
    fun `findGuard() returns the single guard when present`() {
        val expected = Guard(Position(6, 4), Direction.Up)
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
}