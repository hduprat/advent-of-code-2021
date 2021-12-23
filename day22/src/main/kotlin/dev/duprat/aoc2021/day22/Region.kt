package dev.duprat.aoc2021.day22

class Region {
  val xRange: IntRange
  val yRange: IntRange
  val zRange: IntRange

  val cardinal: Long
    get() =
        this.xRange.count().toLong() * this.yRange.count().toLong() * this.zRange.count().toLong()

  fun extractRange(str: String): IntRange {
    val (_, min, max) = str.split("=", "..")
    return min.toInt()..max.toInt()
  }

  constructor(line: String) {
    val (_, ranges) = line.split(" ")

    val (xR, yR, zR) = ranges.split(",").map { extractRange(it) }
    xRange = xR
    yRange = yR
    zRange = zR
  }

  constructor(xRange: IntRange, yRange: IntRange, zRange: IntRange) {
    this.xRange = xRange
    this.yRange = yRange
    this.zRange = zRange
  }

  infix fun intersect(r: Region): Region? {
    val xmin = Math.max(r.xRange.first, this.xRange.first)
    val xmax = Math.min(r.xRange.last, this.xRange.last)
    if (xmin > xmax) return null

    val ymin = Math.max(r.yRange.first, this.yRange.first)
    val ymax = Math.min(r.yRange.last, this.yRange.last)
    if (ymin > ymax) return null

    val zmin = Math.max(r.zRange.first, this.zRange.first)
    val zmax = Math.min(r.zRange.last, this.zRange.last)
    if (zmin > zmax) return null

    return Region(xmin..xmax, ymin..ymax, zmin..zmax)
  }

  infix fun subtract(r: Region): List<Region> {
    val intersection = this.intersect(r)
    if (intersection == null) return listOf(this)

    val regions = mutableListOf<Region>()

    if (zRange.first < intersection.zRange.first)
        regions.add(Region(xRange, yRange, zRange.first..(intersection.zRange.first - 1)))

    if (zRange.last > intersection.zRange.last)
        regions.add(Region(xRange, yRange, (intersection.zRange.last + 1)..zRange.last))

    if (yRange.first < intersection.yRange.first)
        regions.add(
            Region(xRange, yRange.first..(intersection.yRange.first - 1), intersection.zRange)
        )
    if (yRange.last > intersection.yRange.last)
        regions.add(
            Region(xRange, (intersection.yRange.last + 1)..yRange.last, intersection.zRange)
        )

    if (xRange.first < intersection.xRange.first)
        regions.add(
            Region(
                xRange.first..(intersection.xRange.first - 1),
                intersection.yRange,
                intersection.zRange
            )
        )
    if (xRange.last > intersection.xRange.last)
        regions.add(
            Region(
                (intersection.xRange.last + 1)..xRange.last,
                intersection.yRange,
                intersection.zRange
            )
        )

    return regions
  }

  infix fun union(r: Region): List<Region> {
    val intersection = this.intersect(r)
    if (intersection == null) return listOf(this, r)

    val regions = mutableListOf<Region>()
    regions.addAll(this.subtract(r))
    regions.add(r)
    return regions
  }

  override operator fun equals(other: Any?): Boolean {
    if (!(other is Region)) return false
    if (this.xRange != other.xRange) return false
    if (this.yRange != other.yRange) return false
    if (this.zRange != other.zRange) return false
    return true
  }

  override fun toString(): String {
    return "(${this.xRange},${this.yRange},${this.zRange})"
  }
}
