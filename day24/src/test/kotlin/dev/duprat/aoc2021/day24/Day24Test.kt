package dev.duprat.aoc2021.day24

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day24Test {
    @Test
    fun testInitNegator() {
        val scenario = Day24Scenario("negator")
        assertEquals(2, scenario.program.instructions.size)
        assertEquals(1, scenario.program.numArgs)
    }

    @Test
    fun testExecNegator() {
        val scenario = Day24Scenario("negator")
        scenario.exec(8)
        assertEquals(-8, scenario.program.x)
    }

    @Test
    fun testInitIsThreeTimesLarger() {
        val scenario = Day24Scenario("isThreeTimesLarger")
        assertEquals(4, scenario.program.instructions.size)
        assertEquals(2, scenario.program.numArgs)
    }

    @Test
    fun testExecIsThreeTimesLarger() {
        val scenario = Day24Scenario("isThreeTimesLarger")
        scenario.exec(8, 24)
        assertEquals(1, scenario.program.z)
    }

    @Test
    fun testInitBinaryCut() {
        val scenario = Day24Scenario("binaryCut")
        assertEquals(11, scenario.program.instructions.size)
        assertEquals(1, scenario.program.numArgs)
    }

    @Test
    fun testExecBinaryCut() {
        val scenario = Day24Scenario("binaryCut")
        scenario.exec(24)
        assertEquals("1000", scenario.program.context.values.joinToString(""))
    }
}
