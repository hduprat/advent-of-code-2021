/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package dev.duprat.aoc2021.utils

open class Scenario {
    public var lines: List<String>

    constructor() {
        val file = this::class.java.getResource("/input.txt").readText()
        this.lines = file.split('\n').filter { it != "" }
    }
}
