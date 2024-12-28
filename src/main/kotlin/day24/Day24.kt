package day24

import getInput

class Gate(val in1: String, val in2: String, val operator: (Boolean, Boolean) -> Boolean) {
    operator fun invoke(in1: Boolean, in2: Boolean): Boolean = operator(in1, in2)
}

fun Boolean.toULong(): ULong = if (this) 1u else 0u

class Bits(bits: List<Boolean>) : List<Boolean> by bits {
    constructor(value: ULong) : this(generateSequence(value) { it shr 1 }.takeWhile { it != 0uL }
        .map { it and 1u == 1uL }.toList().reversed())

    fun toULong() = fold(0uL) { acc, bit ->
        acc shl 1 or bit.toULong()
    }

    override fun toString() = map(Boolean::toULong).joinToString("")
}

class Circuit(private val initialWires: Map<String, Boolean>, private val gates: Map<String, Gate>) {
    private val connectedWires = buildMap {
        putAll(initialWires)
        val gates = gates.toMutableMap()
        while (gates.isNotEmpty()) {
            gates.filter { with(it.value) { contains(in1) && contains(in2) } }.forEach { (output, gate) ->
                put(output, gate(getValue(gate.in1), getValue(gate.in2)))
                gates.remove(output)
            }
        }
    }

    val output = connectedWires.getBits('z')

    private fun Map<String, Boolean>.getBits(initialChar: Char) =
        filterKeys { it.startsWith(initialChar) }.entries.sortedByDescending { it.key.substring(1).toInt() }
            .map { it.value }.let(::Bits)
}

fun buildCircuit(input: List<String>): Circuit {
    val wires = input.takeWhile { it.contains(':') }.associate {
        val (name, value) = it.split(':')
        name to (value.trim().toInt() != 0)
    }
    val gates = input.drop(wires.size + 1).associate {
        val (in1, op, in2, out) = "(.+) (.+) (.+) -> (.+)".toRegex().find(it)!!.destructured
        val operator = when (op) {
            "AND" -> Boolean::and
            "OR" -> Boolean::or
            else -> Boolean::xor
        }
        out to Gate(in1, in2, operator)
    }
    return Circuit(wires, gates)
}

fun part1(input: List<String>) = buildCircuit(input).output.toULong()
fun part2(input: List<String>) = 0

fun main() {
    val input = getInput(24)
    println(part1(input))
    println(part2(input))
}