package day7

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import kotlin.test.assertEquals

class Day7Test {
    private val testInput = """
        190: 10 19
        3267: 81 40 27
        83: 17 5
        156: 15 6
        7290: 6 8 6 15
        161011: 16 10 13
        192: 17 8 14
        21037: 9 7 18 13
        292: 11 6 16 20
    """.trimIndent().split('\n')

    @Test
    fun operators() {
        val a = 2uL
        val b = 3uL

        assertAll(
            { assertEquals(5u, Operator.Add(a, b)) },
            { assertEquals(6u, Operator.Multiply(a, b)) },
            { assertEquals(23u, Operator.Concatenate(a, b)) },
        )
    }

    @Test
    fun equation_fromString() {
        val input = testInput.first()
        val expected = Equation(190u, listOf(10u, 19u))

        assertEquals(expected, Equation.fromString(input))
    }

    @Test
    fun tuples() {
        val input = listOf(0, 1, 2)
        val expected = listOf(
            listOf(0, 0),
            listOf(0, 1),
            listOf(0, 2),
            listOf(1, 0),
            listOf(1, 1),
            listOf(1, 2),
            listOf(2, 0),
            listOf(2, 1),
            listOf(2, 2)
        )

        assertEquals(expected, input.tuples(2).toList())
    }

    @Test
    fun part1() {
        assertEquals(3749u, part1(testInput))
    }

    @Test
    fun part2() {
        assertEquals(11387u, part2(testInput))
    }
}