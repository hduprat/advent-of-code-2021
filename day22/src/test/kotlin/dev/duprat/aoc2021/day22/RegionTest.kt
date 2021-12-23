package dev.duprat.aoc2021.day22

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RegionTest {
  @Test
  fun testCount() {
    val r = Region(10..12, 10..12, 10..12)
    assertEquals(27, r.cardinal)
  }

  @Test
  fun testIntersection() {
    val r1 = Region(10..12, 10..12, 10..12)
    val r2 = Region(9..11, 9..11, 9..11)

    val r = r1 intersect r2

    assertEquals(Region(10..11, 10..11, 10..11), r)
    assertEquals(8, r?.cardinal)
  }

  @Test
  fun testSubtract() {
    val r1 = Region(10..12, 10..12, 10..12)
    val r2 = Region(9..11, 9..11, 9..11)

    val r = r1 subtract r2

    assertEquals(3, r.size)
    assertEquals(Region(10..12, 10..12, 12..12), r[0])
    assertEquals(Region(10..12, 12..12, 10..11), r[1])
    assertEquals(Region(12..12, 10..11, 10..11), r[2])
    assertEquals(19, r.sumOf { it.cardinal })
  }

  @Test
  fun testUnion() {
    val r1 = Region(10..12, 10..12, 10..12)
    val r2 = Region(9..11, 9..11, 9..11)

    val r = r1 union r2

    assertEquals(4, r.size)
    assertEquals(Region(10..12, 10..12, 12..12), r[0])
    assertEquals(Region(10..12, 12..12, 10..11), r[1])
    assertEquals(Region(12..12, 10..11, 10..11), r[2])
    assertEquals(Region(9..11, 9..11, 9..11), r[3])
    assertEquals(46, r.sumOf { it.cardinal })
  }
}
