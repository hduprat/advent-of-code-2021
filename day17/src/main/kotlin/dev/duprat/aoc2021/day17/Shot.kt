package dev.duprat.aoc2021.day17

class IllegalShotError(initVx: Int, initVy: Int) :
    Error("Illegal shot at initial velocity ($initVx, $initVy)") {}

class Shot(initVx: Int, initVy: Int) {
  init {
    if (initVx < 0) throw IllegalShotError(initVx, initVy)
  }

  private var vx = initVx
  private var vy = initVy
  private var x = 0
  private var y = 0
  public var maxY = initVy * (initVy + 1) / 2
  public var maxX = initVx * (initVx + 1) / 2
  var step = 0

  fun shootAtTarget(xRange: IntRange, yRange: IntRange): Boolean {
    if (maxX < xRange.first) return false
    while (true) {
      x += vx
      y += vy
      if (vx > 0) vx--
      vy--
      step++
      when {
        x in xRange && y in yRange -> return true
        y < yRange.first -> return false
        x > xRange.last -> return false
      }
    }
  }
}
