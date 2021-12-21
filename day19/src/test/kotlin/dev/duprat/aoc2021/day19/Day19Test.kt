package dev.duprat.aoc2021.day19

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day19Test {
    @Test
    fun testInit() {
        val scenario = Day19Scenario()
        assertEquals(5, scenario.scanners.size)
        assertEquals(25, scenario.scanners[0].size)
        assertEquals(25, scenario.scanners[1].size)
        assertEquals(26, scenario.scanners[2].size)
        assertEquals(25, scenario.scanners[3].size)
        assertEquals(26, scenario.scanners[4].size)
    }

    @Test
    fun testCommonBeacons() {
        val scenario = Day19Scenario()
        scenario.checkScannerPositionAndTransform(0, 1)
        assertEquals(Triple(68, -1246, -43), scenario.scannerPositions[1])

        scenario.checkScannerPositionAndTransform(1, 4)
        assertEquals(Triple(-20, -1133, 1061), scenario.scannerPositions[4])

        scenario.checkScannerPositionAndTransform(4, 2)
        assertEquals(Triple(1105, -1205, 1229), scenario.scannerPositions[2])

        scenario.checkScannerPositionAndTransform(1, 3)
        assertEquals(Triple(-92, -2380, -20), scenario.scannerPositions[3])
    }

    @Test
    fun testNumberOfBeacons() {
        val scenario = Day19Scenario()
        scenario.listAllScannersOverlappingWith(0)
        assertEquals(79, scenario.getNumberOfBeacons())
    }

    @Test
    fun testFindLargestDistanceBetweenTwoScanners() {
        val scenario = Day19Scenario()
        scenario.listAllScannersOverlappingWith(0)
        assertEquals(3621, scenario.findLargestDistanceBetweenTwoScanners())
    }
}
