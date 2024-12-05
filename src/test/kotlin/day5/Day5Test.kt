package day5

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day5Test {
    private val testInput = """
        47|53
        97|13
        97|61
        97|47
        75|29
        61|13
        75|53
        29|13
        97|29
        53|29
        61|53
        97|53
        61|29
        47|13
        75|47
        97|75
        47|61
        75|61
        47|29
        75|13
        53|13

        75,47,61,53,29
        97,61,53,29,13
        75,29,13
        75,97,47,61,53
        61,13,29
        97,13,75,29,47
    """.trimIndent().split('\n')

    @Test
    fun process() {
        val input = """
            47|53
            97|13
            
            75,47
            97,61
        """.trimIndent().split('\n')
        val expectedRules = listOf(
            listOf(47, 53), listOf(97, 13)
        )
        val expectedUpdates = listOf(
            listOf(75, 47), listOf(97, 61)
        )

        val (rules, updates) = process(input)

        assertEquals(expectedRules, rules)
        assertEquals(expectedUpdates, updates)
    }

    @Test
    fun validation() {
        val expected = listOf(true, true, true, false, false, false)
        val (sut, updates) = process(testInput)

        assertEquals(expected, updates.map(sut::isValid))
    }

    @Test
    fun sort() {
        val input = listOf(
            listOf(75, 97, 47, 61, 53),
            listOf(61, 13, 29),
            listOf(97, 13, 75, 29, 47)
        )
        val expected = listOf(
            listOf(97, 75, 47, 61, 53),
            listOf(61, 29, 13),
            listOf(97, 75, 47, 29, 13)
        )

        val (sut, _) = process(testInput)

        assertEquals(expected, input.map(sut::sort))
    }

    @Test
    fun part1() {
        assertEquals(143, part1(testInput))
    }

    @Test
    fun part2() {
        assertEquals(123, part2(testInput))
    }
}