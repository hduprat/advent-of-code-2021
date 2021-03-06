/*
 * This Kotlin source file was generated by the Gradle "init" task.
 */
package dev.duprat.aoc2021.day5

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day5Test {
    @Test
    fun testInit() {
        (5..0).map { println(it) }
        val scenario = Day5Scenario()
        assertEquals(10, scenario.lines.size)
        assertEquals(1, scenario.getNumberOfVentsAt(3, 9))
        assertEquals(0, scenario.getNumberOfVentsAt(11, 9))
    }

    @Test
    fun testCountBusyVents() {
        val scenario = Day5Scenario()
        assertEquals(5, scenario.countBusyVents())
    }

    @Test
    fun testCountBusyVentsDiagonalMode() {
        val scenario = Day5Scenario(drawDiagonals = true)
        assertEquals(12, scenario.countBusyVents())
    }
}
