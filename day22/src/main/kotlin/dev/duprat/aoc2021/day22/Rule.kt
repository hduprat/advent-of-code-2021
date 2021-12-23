package dev.duprat.aoc2021.day22

class Rule {
  val region: Region
  val isOff: Boolean

  fun extractRange(str: String): IntRange {
    val (_, min, max) = str.split("=", "..")
    return min.toInt()..max.toInt()
  }

  fun isOutOfInitialRange(): Boolean {
    if (region.xRange.first > 50 || region.xRange.first < -50) return true
    if (region.xRange.last > 50 || region.xRange.last < -50) return true
    if (region.yRange.first > 50 || region.yRange.first < -50) return true
    if (region.yRange.last > 50 || region.yRange.last < -50) return true
    if (region.zRange.first > 50 || region.zRange.first < -50) return true
    if (region.zRange.last > 50 || region.zRange.last < -50) return true
    return false
  }

  constructor(line: String) {
    val (ruleType, ranges) = line.split(" ")
    isOff = ruleType == "off"

    val (xR, yR, zR) = ranges.split(",").map { extractRange(it) }
    region = Region(xR, yR, zR)
  }

  fun applyTo(regions: List<Region>): List<Region> {
    if (regions.isEmpty()) return if (isOff) regions else listOf(region)
    return regions
        .flatMap {
          when (isOff) {
            true -> it subtract region
            else -> it union region
          }
        }
        .distinct()
  }
}
