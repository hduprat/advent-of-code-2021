package dev.duprat.aoc2021.day20

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day20Test {
    @Test
    fun testInit() {
        val scenario = Day20Scenario()
        assertEquals(512, scenario.algorithm.length)
        assertEquals(10, scenario.image.size)
    }

    @Test
    fun testEnhance() {
        val scenario = Day20Scenario()
        scenario.enhance()
        assertEquals(24, scenario.image.size)
        scenario.enhance()
        assertEquals(35, scenario.image.size)
    }

    @Test
    fun testEnhanceALot() {
        val scenario = Day20Scenario()
        scenario.enhance(50)
        assertEquals(3351, scenario.image.size)
    }
}
