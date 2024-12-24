package day22

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day22Test {

    @Test
    fun secrets() {
        val expected = listOf<Long>(
            15887950, 16495136, 527345, 704524, 1553684, 12683156, 11100544, 12249484, 7753432, 5908254
        )
        assertEquals(expected, secrets(123).drop(1).take(10).toList())
    }

    @Test
    fun prices() {
        val expected = listOf<Long>(
            3, 0, 6, 5, 4, 4, 6, 4, 4, 2
        )

        assertEquals(expected, prices(123).take(10).toList())
    }

    @Test
    fun changeSequence() {
        val expected = listOf(
            0 to -3, 6 to 6, 5 to -1, 4 to -1, 4 to 0, 6 to 2, 4 to -2, 4 to 0, 2 to -2
        ).map { PriceChange(it.first.toLong(), it.second.toLong()) }

        assertEquals(expected, changeSequence(123).take(9).toList())
    }

    @Test
    fun bestPrice() {
        val input = listOf(
            0 to -3, 6 to 6, 5 to -1, 4 to -1, 4 to 0, 6 to 2, 4 to -2, 4 to 0, 2 to -2
        ).map { PriceChange(it.first.toLong(), it.second.toLong()) }
        val sut = bestPrice(input)
        val expectedPrice = 6L
        val expectedChanges = listOf<Long>(-1, -1, 0, 2)

        assertEquals(expectedPrice, sut.last().price)
        assertEquals(expectedChanges, sut.map { it.change })
    }

    @Test
    fun part1() {
        val input = """
            1
            10
            100
            2024
        """.trimIndent().split('\n')

        assertEquals(37327623, part1(input))
    }

    @Test
    fun part2() {
        val input = """
            1
            2
            3
            2024
        """.trimIndent().split('\n')

        assertEquals(23, part2(input))
    }
}