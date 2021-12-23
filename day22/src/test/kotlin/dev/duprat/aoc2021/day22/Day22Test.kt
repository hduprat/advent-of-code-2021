package dev.duprat.aoc2021.day22

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day22Test {
    @Test
    fun testInit() {
        val scenario = Day22Scenario()
        assertEquals(20, scenario.initRules.size)
        assertEquals(-29..23, scenario.initRules[2].region.yRange)
    }

    @Test
    fun testCountLitCubesInit() {
        val scenario = Day22Scenario()
        assertEquals(590784, scenario.countLitCubes())
    }

    @Test
    fun testCountLitCubesReboot() {
        val scenario = Day22Scenario("/biggerInput.txt")
        scenario.reboot()
        assertEquals(2758514936282235, scenario.countLitCubes())
    }
}
