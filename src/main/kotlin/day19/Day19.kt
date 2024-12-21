package day19

import getInput

class TowelMaker(private val patterns: List<String>) {
    private val matches = mutableMapOf<String, ULong>()

    fun canMake(towel: String): Boolean = when {
        towel.isEmpty() -> true
        else -> matchingPatterns(towel).any { canMake(towel.substring(it.length)) }
    }

    fun optionCount(towel: String): ULong = when {
        towel.isEmpty() -> 1u
        else -> matches.getOrPut(towel) {
            matchingPatterns(towel).sumOf { optionCount(towel.substring(it.length)) }
        }
    }

    private fun matchingPatterns(input: String) = patterns.filter { input.startsWith(it) }
}

fun process(input: List<String>) = input.first().split(',').map(String::trim).let(::TowelMaker) to input.drop(2)

fun part1(input: List<String>): Int {
    val (towelMaker, towels) = process(input)
    return towels.count { towelMaker.canMake(it) }
}

fun part2(input: List<String>): ULong {
    val (towelMaker, towels) = process(input)
    return towels.sumOf { towelMaker.optionCount(it) }
}

fun main() {
    val input = getInput(19)
    println(part1(input))
    println(part2(input))
}