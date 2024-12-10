package day9

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import kotlin.test.assertEquals

class Day9Test {
    private val testInput = "2333133121414131402"

    @Test
    fun disk() {
        val sut = Disk(testInput)
        val expectedLayout = "00...111...2...333.44.5555.6666.777.888899"
        assertEquals(expectedLayout, sut.toString())
    }

    @Test
    fun defragment() {
        val sut = Disk(testInput)
        val expectedLayout = "0099811188827773336446555566.............."
        sut.defragment()
        assertEquals(expectedLayout, sut.toString())
    }

    @Test
    fun part1() {
        assertEquals(1928u, part1(testInput))
    }

    @Test
    fun part2() {
    }
}