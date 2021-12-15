/*
 * This Kotlin source file was generated by the Gradle "init" task.
 */
package dev.duprat.aoc2021.day11

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day11Test {
    @Test
    fun testInit() {
        val scenario = Day11Scenario()
        assertEquals(10, scenario.octopi.size)
        assertEquals(5, scenario.octopi[0][0])
        assertEquals(6, scenario.octopi[9][9])
    }

    @Test
    fun testFlashes() {
        val scenario = Day11Scenario()

        scenario.goToStep(1)
        assertEquals(0, scenario.totalFlashes)

        scenario.goToStep(2)
        assertEquals(35, scenario.totalFlashes)

        scenario.goToStep(10)
        assertEquals(204, scenario.totalFlashes)

        scenario.goToStep(100)
        assertEquals(1656, scenario.totalFlashes)
    }

    @Test
    fun testGoToFirstCollectingFlashStep() {
        val scenario = Day11Scenario()

        scenario.goToFirstCollectingFlashStep()
        assertEquals(195, scenario.step)
    }
}