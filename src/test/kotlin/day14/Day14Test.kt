package day14

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day14Test {
    private val testInput = """
        p=0,4 v=3,-3
        p=6,3 v=-1,-3
        p=10,3 v=-1,2
        p=2,0 v=2,-1
        p=0,0 v=1,3
        p=3,0 v=-2,-2
        p=7,6 v=-1,-3
        p=3,0 v=-1,-2
        p=9,3 v=2,3
        p=7,3 v=-1,2
        p=2,4 v=2,-3
        p=9,5 v=-3,-3
    """.trimIndent().split('\n')

    @Test
    fun roboMap() {
        val expected = """
            ......2..1.
            ...........
            1..........
            .11........
            .....1.....
            ...12......
            .1....1....
        """.trimIndent()

        val sut = createRoboMap(testInput, 11, 7)
        repeat(100) { sut.step() }

        assertEquals(expected, sut.toString())
    }

    @Test
    fun part1() {
        assertEquals(12u, part1(testInput, 11, 7))
    }

    @Test
    fun part2() {
        assertEquals(1, part2(testInput))
    }
}