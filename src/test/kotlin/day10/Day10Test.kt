package day10

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day10Test {
    private val testInput = """
        89010123
        78121874
        87430965
        96549874
        45678903
        32019012
        01329801
        10456732
    """.trimIndent().split('\n')

    @Test
    fun trailMap() {
        val sut = TrailMap.fromStringList(testInput)
        val expectedPositions = listOf(
            listOf(0, 2),
            listOf(0, 4),
            listOf(2, 4),
            listOf(4, 6),
            listOf(5, 2),
            listOf(5, 5),
            listOf(6, 0),
            listOf(6, 6),
            listOf(7, 1)
        ).map{ (a, b) -> Location(a, b, 0)}.toSet()

        assertEquals(expectedPositions, sut.trailHeads)
    }

    @Test
    fun score() {
        val input = """
            ..90..9
            ...1.98
            ...2..7
            6543456
            765.987
            876....
            987....
        """.trimIndent().split('\n')
        val sut = TrailMap.fromStringList(input)

        assertEquals(4, sut.score)
    }

    @Test
    fun part1() {
        assertEquals(36, part1(testInput))
    }

    @Test
    fun part2() {
        assertEquals(81, part2(testInput))
    }
}