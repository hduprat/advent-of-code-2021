/*
 * This Kotlin source file was generated by the Gradle "init" task.
 */
package dev.duprat.aoc2021.day2

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day2Test {
    @Test
    fun testInit() {
        val scenario = Day2Scenario()
        assertEquals(Pair(0, 0), scenario.position)
    }

    @Test
    fun testExecuteAimModeStep() {
        val scenario = Day2Scenario()

        scenario.executeAimModeStep("down 4")
        assertEquals(Pair(0, 0), scenario.position)

        scenario.executeAimModeStep("forward 5")
        assertEquals(Pair(5, 20), scenario.position)

        scenario.executeAimModeStep("up 2")
        scenario.executeAimModeStep("forward 3")
        assertEquals(Pair(8, 26), scenario.position)

        scenario.executeAimModeStep("up 10")
        scenario.executeAimModeStep("forward 2")
        assertEquals(Pair(10, 10), scenario.position)
    }

    @Test
    fun testExecuteStep() {
        val scenario = Day2Scenario()

        scenario.executeStep("forward 5")
        assertEquals(Pair(5, 0), scenario.position)

        scenario.executeStep("down 7")
        assertEquals(Pair(5, 7), scenario.position)

        scenario.executeStep("up 1")
        assertEquals(Pair(5, 6), scenario.position)
    }

    @Test
    fun testExecuteAllSteps() {
        val scenario = Day2Scenario()

        scenario.executeAllSteps()
        assertEquals(Pair(15, 10), scenario.position)
    }

    @Test
    fun testExecuteAllAimModeSteps() {
        val scenario = Day2Scenario(aimMode = true)

        scenario.executeAllSteps()
        assertEquals(Pair(15, 60), scenario.position)
    }
}
