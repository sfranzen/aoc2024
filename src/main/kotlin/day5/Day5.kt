package day5

import getInput

typealias Rule = List<Int>
typealias Update = List<Int>

val input = getInput(5).readLines()

fun process(input: List<String>): Pair<RuleSet, List<Update>> =
    input.filter(String::isNotBlank).partition { it.contains('|') }.run {
        first.map { it.split('|').map(String::toInt) }.let(::RuleSet) to second.map { it.split(',').map(String::toInt) }
    }

data class RuleSet(val rules: List<Rule>) : List<Rule> by rules {
    fun validate(update: Update): Boolean = matchingRules(update).all {
        it.map { page -> update.indexOf(page) }.windowed(2).all { (a, b) -> a < b }
    }

    private fun matchingRules(update: Update): List<Rule> = rules.filter { update.containsAll(it) }
}

fun part1(input: List<String>): Int {
    val (ruleSet, updates) = process(input)
    return updates.filter { ruleSet.validate(it) }.sumOf { it[it.size / 2] }
}

fun part2(input: List<String>) = 0

fun main() {
    println(part1(input))
}