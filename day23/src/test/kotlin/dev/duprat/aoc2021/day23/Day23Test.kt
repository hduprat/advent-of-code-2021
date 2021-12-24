package dev.duprat.aoc2021.day23

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day23Test {
    @Test
    fun testInit() {
        val scenario = Day23Scenario()
        assertEquals(4, scenario.rooms.size)
        assertEquals("B,A", scenario.rooms[0].toString())
        assertEquals("C,D", scenario.rooms[1].toString())
        assertEquals("B,C", scenario.rooms[2].toString())
        assertEquals("D,A", scenario.rooms[3].toString())
    }
}
