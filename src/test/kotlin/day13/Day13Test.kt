package day13

import Vector2D
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day13Test {
    private val testInput = """
        Button A: X+94, Y+34
        Button B: X+22, Y+67
        Prize: X=8400, Y=5400

        Button A: X+26, Y+66
        Button B: X+67, Y+21
        Prize: X=12748, Y=12176

        Button A: X+17, Y+86
        Button B: X+84, Y+37
        Prize: X=7870, Y=6450

        Button A: X+69, Y+23
        Button B: X+27, Y+71
        Prize: X=18641, Y=10279
    """.trimIndent().split('\n')

    @Test
    fun createMachines() {
        val expected = listOf(
            ClawMachine(listOf(Button(94, 34), Button(22, 67)), Vector2D(8400, 5400)),
            ClawMachine(listOf(Button(26, 66), Button(67, 21)), Vector2D(12748, 12176)),
            ClawMachine(listOf(Button(17, 86), Button(84, 37)), Vector2D(7870, 6450)),
            ClawMachine(listOf(Button(69, 23), Button(27, 71)), Vector2D(18641, 10279)),
        )

        assertEquals(expected, createMachines(testInput))
    }

    @Test
    fun part1() {
        assertEquals(480, part1(testInput))
    }
}