package dev.duprat.aoc2021.day24

abstract class Instruction {
  abstract fun exec(ctx: MutableProgramContext)
}

class Input : Instruction {
  val targetVar: String

  constructor(targetVar: String) : super() {
    this.targetVar = targetVar
  }

  override fun exec(ctx: MutableProgramContext) {}

  fun exec(arg: Long, ctx: MutableProgramContext) {
    ctx.put(targetVar, arg)
  }

  override fun toString() = "inp $targetVar"
}

abstract class TwoOperandInstruction : Instruction {
  val targetVar: String
  val inputVar: String

  fun valueOf(input: String, ctx: ProgramContext): Long {
    return ctx.getOrElse(input) {
      val value = input.toIntOrNull()
      if (value == null) throw ALUProgramError("variable not found: $input") else value.toLong()
    }
  }

  constructor(targetVar: String, inputVar: String) : super() {
    this.targetVar = targetVar
    this.inputVar = inputVar
  }
}

class Add : TwoOperandInstruction {
  constructor(targetVar: String, inputVar: String) : super(targetVar, inputVar) {}

  override fun exec(ctx: MutableProgramContext) {
    ctx.put(targetVar, valueOf(targetVar, ctx) + valueOf(inputVar, ctx))
  }

  override fun toString() = "add $targetVar $inputVar"
}

class Multiply : TwoOperandInstruction {
  constructor(
      targetVar: String,
      inputVar: String,
  ) : super(targetVar, inputVar) {}

  override fun exec(ctx: MutableProgramContext) {
    ctx.put(targetVar, valueOf(targetVar, ctx) * valueOf(inputVar, ctx))
  }

  override fun toString() = "mul $targetVar $inputVar"
}

class Divide : TwoOperandInstruction {
  constructor(
      targetVar: String,
      inputVar: String,
  ) : super(targetVar, inputVar) {}

  override fun exec(ctx: MutableProgramContext) {
    ctx.put(targetVar, valueOf(targetVar, ctx) / valueOf(inputVar, ctx))
  }

  override fun toString() = "div $targetVar $inputVar"
}

class Modulo : TwoOperandInstruction {
  constructor(
      targetVar: String,
      inputVar: String,
  ) : super(targetVar, inputVar) {}

  override fun exec(ctx: MutableProgramContext) {
    ctx.put(targetVar, valueOf(targetVar, ctx) % valueOf(inputVar, ctx))
  }

  override fun toString() = "mod $targetVar $inputVar"
}

class Equals : TwoOperandInstruction {
  constructor(
      targetVar: String,
      inputVar: String,
  ) : super(targetVar, inputVar) {}

  override fun exec(ctx: MutableProgramContext) {
    ctx.put(targetVar, if (valueOf(targetVar, ctx) == valueOf(inputVar, ctx)) 1 else 0)
  }

  override fun toString() = "eql $targetVar $inputVar"
}
