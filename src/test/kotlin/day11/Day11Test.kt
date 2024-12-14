package day11

import kotlin.test.Test
import kotlin.test.assertEquals

class Day11Test {
    private val testInput = "125 17"

    @Test
    fun blink() {
        val input = "0 1 10 99 999"
        val expected = "1 2024 1 0 9 9 2021976"

        assertEquals(expected, blink(input))
    }

    @Test
    fun blinkSequence() {
        val input = "125 17"
        val expected = listOf(
            "253000 1 7",
            "253 0 2024 14168",
            "512072 1 20 24 28676032",
            "512 72 2024 2 0 2 4 2867 6032",
            "1036288 7 2 20 24 4048 1 4048 8096 28 67 60 32",
            "2097446912 14168 4048 2 0 2 4 40 48 2024 40 48 80 96 2 8 6 7 6 0 3 2"
        )
        val sut = blinkSequence(input)

        assertEquals(expected, sut.take(6).toList())
    }

    @Test
    fun part1() {
        assertEquals(55312, part1(testInput))
    }

    @Test
    fun part2() {
        assertEquals(1, part2(testInput))
    }
}