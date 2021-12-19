package dev.duprat.aoc2021.day18

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day18Test {
    @Test
    fun testInit() {
        val scenario = Day18Scenario()
        assertEquals(10, scenario.lines.size)
    }

    @Test
    fun testMagnitude() {
        val scenario = Day18Scenario()
        scenario.addAllHomework()
        assertEquals(
                "[[[[6,6],[7,6]],[[7,7],[7,0]]],[[[7,7],[7,7]],[[7,8],[9,9]]]]",
                scenario.n.baseNode.getValue()
        )
        assertEquals(4140, scenario.n.magnitude)
    }

    @Test
    fun testHighestMagnitudeOfTwo() {
        val scenario = Day18Scenario()
        assertEquals(3993, scenario.findHighestMagnitude())
    }
}
