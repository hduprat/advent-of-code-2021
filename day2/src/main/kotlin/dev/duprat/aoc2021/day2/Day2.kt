/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package dev.duprat.aoc2021.day2

import dev.duprat.aoc2021.utils.Scenario

class Day2Scenario : Scenario {
    public var x = 0
    public var depth = 0
    private var aim = 0
    private var aimMode: Boolean

    val position
        get(): Pair<Int, Int> {
            return Pair(x, depth)
        }

    constructor(aimMode: Boolean = false) : super() {
        this.aimMode = aimMode
    }

    fun executeStep(step: String) {
        val (command, value) = step.split(' ')
        when (command) {
            "forward" -> this.x += value.toInt()
            "down" -> this.depth += value.toInt()
            "up" -> this.depth -= value.toInt()
        }
    }

    fun executeAimModeStep(step: String) {
        val (command, value) = step.split(' ')
        when (command) {
            "forward" -> {
                this.x += value.toInt()
                this.depth += this.aim * value.toInt()
            }
            "down" -> this.aim += value.toInt()
            "up" -> this.aim -= value.toInt()
        }
    }

    fun executeAllSteps() {
        if (aimMode) this.lines.map { executeAimModeStep(it) }
        else this.lines.map { executeStep(it) }
    }
}

fun main() {
    println("Exercice 1")
    val exercise1 = Day2Scenario()
    exercise1.executeAllSteps()
    println("Résultat: ${exercise1.x * exercise1.depth}")

    println("Exercice 2")
    val exercise2 = Day2Scenario(aimMode = true)
    exercise2.executeAllSteps()
    println("Résultat: ${exercise2.x * exercise2.depth}")
}
