package day4

import kotlin.test.Test
import kotlin.test.assertEquals

class Day4Test {
    private val testInput = listOf(
        "MMMSXXMASM",
        "MSAMXMSMSA",
        "AMXSXMAAMM",
        "MSAMASMSMX",
        "XMASAMXAMM",
        "XXAMMXXAMA",
        "SMSMSASXSS",
        "SAXAMASAAA",
        "MAMMMXMMMM",
        "MXMXAXMASX",
    )

    @Test
    fun part1() {
        assertEquals(18, part1(testInput))
    }

    @Test
    fun part2() {
        assertEquals(9, part2(testInput))
    }
}