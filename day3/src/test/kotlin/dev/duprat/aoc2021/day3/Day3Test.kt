/*
 * This Kotlin source file was generated by the Gradle "init" task.
 */
package dev.duprat.aoc2021.day3

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day3Test {
    @Test
    fun testInit() {
        val scenario = Day3Scenario()
        assertEquals(12, scenario.lines.size)
    }

    @Test
    fun testGammaRate() {
        val scenario = Day3Scenario()
        scenario.computeGammaRate()
        assertEquals(22, scenario.gammaRate)
    }

    @Test
    fun testEpsilonRate() {
        val scenario = Day3Scenario()
        scenario.computeEpsilonRate()
        assertEquals(9, scenario.epsilonRate)
    }

    @Test
    fun testOxygenGeneratorRating() {
        val scenario = Day3Scenario()
        scenario.computeOxygenGeneratorRating()
        assertEquals(23, scenario.oxygenGeneratorRating)
    }

    @Test
    fun testCo2ScrubberRating() {
        val scenario = Day3Scenario()
        scenario.computeCo2ScrubberRating()
        assertEquals(10, scenario.co2ScrubberRating)
    }

    @Test
    fun testPowerConsumption() {
        val scenario = Day3Scenario()

        assertEquals(198, scenario.getPowerConsumption())
    }

    @Test
    fun testLifeSupportRating() {
        val scenario = Day3Scenario()

        assertEquals(230, scenario.getLifeSupportRating())
    }
}
