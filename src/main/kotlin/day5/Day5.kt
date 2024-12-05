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
    fun isValid(update: Update): Boolean = rules.filter(update::containsAll).all {
        it.map(update::indexOf).windowed(2).all { (a, b) -> a < b }
    }

    fun sort(update: Update): Update = update.sortedWith { a, b ->
        rules.find { a in it && b in it }?.run { indexOf(a) - indexOf(b) } ?: 0
    }
}

fun part1(input: List<String>): Int {
    val (ruleSet, updates) = process(input)
    return updates.filter(ruleSet::isValid).sumOf { it[it.size / 2] }
}

fun part2(input: List<String>): Int {
    val (ruleSet, updates) = process(input)
    return updates.filterNot(ruleSet::isValid).map(ruleSet::sort).sumOf { it[it.size / 2] }
}

fun main() {
    println(part1(input))
    println(part2(input))
}