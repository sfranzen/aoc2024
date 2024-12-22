package day20

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day20Test {
    private val testInput = """
        ###############
        #...#...#.....#
        #.#.#.#.#.###.#
        #S#...#.#.#...#
        #######.#.#.###
        #######.#.#...#
        #######.#.###.#
        ###..E#...#...#
        ###.#######.###
        #...###...#...#
        #.#####.#.###.#
        #.#...#.#.#...#
        #.#.#.#.#.#.###
        #...#...#...###
        ###############
    """.trimIndent().split('\n')

    @Test
    fun part1() {
        val sut = RaceTrack(testInput)
        val expectedSavings = mapOf(
            2 to 14, 4 to 14, 6 to 2, 8 to 4, 10 to 2, 12 to 3, 20 to 1, 36 to 1, 38 to 1, 40 to 1, 64 to 1
        )
        val actualSavings = sut.evaluateCheats().groupBy { it }.map { it.key to it.value.size }.toMap()

        assertEquals(85, sut.path.size)
        assertEquals(expectedSavings, actualSavings)
    }

    @Test
    fun part2() {
        val sut = RaceTrack(testInput)
        val expectedSavings = mapOf(
            50 to 32,
            52 to 31,
            54 to 29,
            56 to 39,
            58 to 25,
            60 to 23,
            62 to 20,
            64 to 19,
            66 to 12,
            68 to 14,
            70 to 12,
            72 to 22,
            74 to 4,
            76 to 3
        )
        val actualSavings = sut.evaluateImprovedCheats().filter { it >= 50 }.groupBy { it }.map { it.key to it.value.size }.toMap()
        assertEquals(expectedSavings, actualSavings)
    }
}