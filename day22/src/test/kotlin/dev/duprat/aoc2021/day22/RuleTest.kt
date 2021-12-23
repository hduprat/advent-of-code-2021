package dev.duprat.aoc2021.day22

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RuleTest {
  @Test
  fun testCount() {
    val r = Rule("on x=10..12,y=10..12,z=10..12")
    assertEquals(27, r.region.cardinal)
    assertEquals(false, r.isOff)
  }

  @Test
  fun testApply() {
    val r = Rule("on x=10..12,y=10..12,z=10..12")
    val regions = r.applyTo(listOf<Region>())
    assertEquals(27, regions.sumOf { it.cardinal })
  }

  @Test
  fun testApplySeveral() {
    val rules =
        listOf(
            Rule("on x=10..12,y=10..12,z=10..12"),
            Rule("on x=11..13,y=11..13,z=11..13"),
            Rule("off x=9..11,y=9..11,z=9..11"),
            Rule("on x=10..10,y=10..10,z=10..10")
        )
    val afterRule0 = rules[0].applyTo(listOf())
    println(afterRule0)
    val afterRule1 = rules[1].applyTo(afterRule0)
    println(afterRule1)
    val regions = rules.fold(listOf<Region>()) { list, rule -> rule.applyTo(list) }
    assertEquals(39, regions.sumOf { it.cardinal })
  }
}
