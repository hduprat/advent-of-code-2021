package dev.duprat.aoc2021.day24

class ALUProgramError(reason: String) : Error("Error in ALU program: $reason")

typealias ProgramContext = Map<String, Long>

typealias MutableProgramContext = MutableMap<String, Long>

class Program {
  val context: MutableProgramContext = mutableMapOf("w" to 0, "x" to 0, "y" to 0, "z" to 0)
  val instructions: List<Instruction>
  val numArgs: Int

  val w: Long
    get() = context["w"] ?: throw ALUProgramError("Variable not found : w")

  val x: Long
    get() = context["x"] ?: throw ALUProgramError("Variable not found : x")

  val y: Long
    get() = context["y"] ?: throw ALUProgramError("Variable not found : y")

  val z: Long
    get() = context["z"] ?: throw ALUProgramError("Variable not found : z")

  constructor(instructions: List<Instruction>) {
    this.instructions = instructions
    numArgs = instructions.count { it is Input }
  }

  fun resetContext(initialContext: ProgramContext) {
    context.putAll(initialContext)
  }

  fun exec(vararg input: Long, initialContext: ProgramContext = context.mapValues { 0 }) {
    var i = 0
    resetContext(initialContext)
    instructions.forEach {
      if (it is Input) {
        it.exec(input[i], context)
        i++
      } else it.exec(context)
    }
  }

  fun extractSubPrograms(): List<Program> {
    var list = instructions.toList()
    var subPrograms = mutableListOf<Program>()
    while (list.isNotEmpty()) {
      val lastInputIndex = list.indexOfLast { it is Input }
      if (lastInputIndex == -1) throw ALUProgramError("No input in program")
      subPrograms.add(0, Program(list.drop(lastInputIndex)))
      list = list.take(lastInputIndex)
    }

    return subPrograms
  }
}
