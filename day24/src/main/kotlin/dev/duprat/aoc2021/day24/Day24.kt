package dev.duprat.aoc2021.day24

import dev.duprat.aoc2021.utils.Scenario

class Day24Scenario : Scenario {
  val program: Program

  fun createInstruction(line: String): Instruction {
    val terms = line.split(" ")
    val command = terms[0]
    val a = terms[1]
    if (command == "inp") return Input(a)
    val b = terms[2]
    return when (command) {
      "add" -> Add(a, b)
      "mul" -> Multiply(a, b)
      "div" -> Divide(a, b)
      "mod" -> Modulo(a, b)
      "eql" -> Equals(a, b)
      else -> throw ALUProgramError("Command $command not found")
    }
  }

  constructor(programName: String) : super("/$programName.txt") {
    program = Program(lines.map { createInstruction(it) })
  }

  fun exec(vararg args: Long) {
    program.exec(*args)
  }

  fun getValuesFor(
      input: MutableMap<Long, Long>,
      program: Program,
      keepSmallest: Boolean = false
  ): MutableMap<Long, Long> {
    var output: MutableMap<Long, Long> = mutableMapOf()
    input.forEach { (key, value) ->
      output.putAll(
          (if (keepSmallest) 9 downTo 1 else 1..9)
              .associateBy({
                program.exec(
                    it.toLong(),
                    initialContext = mapOf("w" to 0, "x" to 0, "y" to 0, "z" to key)
                )
                program.z
              }) { value * 10 + it.toLong() }
              .toMutableMap()
      )
    }

    return output
  }

  fun analyze(smallest: Boolean = false) {
    val subPrograms = program.extractSubPrograms()
    var zValues: MutableMap<Long, Long> =
        subPrograms.fold(mutableMapOf(0L to 0L)) { values, subProgram ->
          getValuesFor(values, subProgram, smallest)
        }

    println(zValues[0])
  }
}

fun main() {
  val MONAD = Day24Scenario("MONAD")
  println("Exercice 1")
  MONAD.analyze()
  println("Exercice 2")
  MONAD.analyze(smallest = true)
}
