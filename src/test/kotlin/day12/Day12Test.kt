package day12

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import kotlin.test.assertEquals

class Day12Test {
    private val testInput = """
        RRRRIICCFF
        RRRRIICCCF
        VVRRRCCFFF
        VVRCCCJFFF
        VVVVCJJCFE
        VVIVCCJJEE
        VVIIICJJEE
        MIIIIIJJEE
        MIIISIJEEE
        MMMISSJEEE
    """.trimIndent().split('\n')

    @Test
    fun gardenPlots() {
        val input = """
            AAAA
            BBCD
            BBCC
            EEEC
        """.trimIndent().split('\n')

        val sut = GardenPlots(input)
        val plots = sut.plots
        val expectedSizes = mapOf('A' to 4, 'B' to 4, 'C' to 4, 'D' to 1, 'E' to 3)
        val expectedPerimeters = mapOf('A' to 10, 'B' to 8, 'C' to 10, 'D' to 4, 'E' to 8)

        assertEquals(5, plots.size)
        assertAll(
            plots.map {
                { assertEquals(1, it.distinctBy(Plant::value).size) }
            })
        assertAll(
            plots.map {
                { assertEquals(expectedSizes[it[0].value], it.size) }
            })
        assertAll(
            plots.map {
                { assertEquals(expectedPerimeters[it[0].value], sut.perimeter(it)) }
            })
    }

    @Test
    fun part1() {
        assertEquals(1930, part1(testInput))
    }
}