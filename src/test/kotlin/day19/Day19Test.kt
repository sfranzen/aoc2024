package day19

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day19Test {
    private val testInput = """
        r, wr, b, g, bwu, rb, gb, br

        brwrr
        bggr
        gbbr
        rrbgbr
        ubwu
        bwurrg
        brgr
        bbrgwb
    """.trimIndent().split('\n')

    @Test
    fun towelMaker() {
        val (sut, towels) = process(testInput)
        val expected = listOf(true, true, true, true, false, true, true, false)

        assertEquals(expected, towels.map(sut::canMake))
    }

    @Test
    fun part1() {
        assertEquals(6, part1(testInput))
    }

    @Test
    fun part2() {
        assertEquals(16u, part2(testInput))
    }
}