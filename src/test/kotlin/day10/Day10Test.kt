package day10

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day10Test {
    val testInput = """
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
    fun heightMap() {
        val sut = HeightMap.fromStringList(testInput)
    }

    @Test
    fun part1() {
        assertEquals(36, part1(""))
    }

    @Test
    fun part2() {
    }
}