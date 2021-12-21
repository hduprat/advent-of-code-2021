package dev.duprat.aoc2021.utils.VectorUtils

typealias Point3D = Triple<Int, Int, Int>

fun diff(b: Point3D, a: Point3D): Point3D {
  return Triple(b.first - a.first, b.second - a.second, b.third - a.third)
}

fun sum(a: Point3D, b: Point3D): Point3D {
  return Triple(b.first + a.first, b.second + a.second, b.third + a.third)
}

fun rotateX(p: Point3D, times: Int = 1): Point3D {
  if (times == 0) return p
  val rotated = Triple(p.first, -p.third, p.second)
  if (times == 1) return rotated
  return rotateX(rotated, times - 1)
}

fun rotateY(p: Point3D, times: Int = 1): Point3D {
  if (times == 0) return p
  val rotated = Triple(-p.third, p.second, p.first)
  if (times == 1) return rotated
  return rotateY(rotated, times - 1)
}

fun rotateZ(p: Point3D, times: Int = 1): Point3D {
  if (times == 0) return p
  val rotated = Triple(p.second, -p.first, p.third)
  if (times == 1) return rotated
  return rotateZ(rotated, times - 1)
}

fun norm1(p: Point3D): Int {
  return Math.abs(p.first) + Math.abs(p.second) + Math.abs(p.third)
}
