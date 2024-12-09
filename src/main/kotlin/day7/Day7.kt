package day7

import getInput
import tuples

enum class Operator {
    Add, Multiply, Concatenate;

    operator fun invoke(lhs: ULong, rhs: ULong): ULong = when (this) {
        Add -> lhs + rhs
        Multiply -> lhs * rhs
        Concatenate -> "$lhs$rhs".toULong()
    }
}

data class Equation(val result: ULong, val numbers: List<ULong>) {
    fun validateAll(operators: Collection<Operator>): Boolean = operators.tuples(numbers.size - 1).any(::validateOne)

    fun validateOne(operators: Collection<Operator>): Boolean =
        result == operators.zip(numbers.drop(1)).fold(numbers[0]) { acc, (operator, number) -> operator(acc, number) }

    companion object {
        fun fromString(input: String) =
            """\d+""".toRegex().findAll(input).toList().map { it.value.toULong() }.let { Equation(it[0], it.drop(1)) }
    }
}

fun calibrate(input: List<String>, operators: Collection<Operator> = Operator.entries): ULong =
    input.map(Equation::fromString).filter { it.validateAll(operators) }.sumOf { it.result }

fun part1(input: List<String>) = calibrate(input, operators = listOf(Operator.Add, Operator.Multiply))

fun part2(input: List<String>) = calibrate(input)

fun main() {
    val input = getInput(7)
    println(part1(input))
    println(part2(input))
}