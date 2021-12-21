package dev.duprat.aoc2021.day19

import dev.duprat.aoc2021.utils.Scenario
import dev.duprat.aoc2021.utils.VectorUtils.*

typealias ScannerData = List<Point3D>

typealias Transform<T> = (T) -> T

val transforms: List<(Point3D) -> Point3D> = run {
  val list = mutableListOf<(Point3D) -> Point3D>()
  // orientations along x
  list.addAll((0..3).map { n -> { rotateX(it, n) } })
  list.addAll((0..3).map { n -> { rotateX(rotateY(it, 2), n) } })
  // orientations along y
  list.addAll((0..3).map { n -> { rotateY(rotateZ(it), n) } })
  list.addAll((0..3).map { n -> { rotateY(rotateZ(it, 3), n) } })
  // orientations along z
  list.addAll((0..3).map { n -> { rotateZ(rotateY(it), n) } })
  list.addAll((0..3).map { n -> { rotateZ(rotateY(it, 3), n) } })

  list
}

class Day19Scenario : Scenario {
  val scanners: List<ScannerData>
  val scannerPositions: MutableList<Point3D>
  val scannerOrientationTransforms: MutableList<Transform<Point3D>>

  constructor() : super() {
    var nLine = 0
    val scannerList = mutableListOf<ScannerData>()
    while (nLine < lines.size) {
      val scannerLines = lines.drop(nLine).takeWhile { it != "" }
      nLine += scannerLines.size + 1
      scannerList.add(
          scannerLines.drop(1).map {
            val ints = it.split(",").map { n -> n.toInt() }
            Triple(ints[0], ints[1], ints[2])
          }
      )
    }

    scanners = scannerList
    scannerPositions = scanners.mapTo(mutableListOf()) { Triple(0, 0, 0) }
    scannerOrientationTransforms = scanners.mapTo(mutableListOf()) { { it } }
  }

  fun checkScannerPositionAndTransform(ref: Int, compared: Int) {
    val scannerRef = scanners[ref]
    val scanner = scanners[compared]

    transforms.forEach { transform ->
      val transformedScanner = scanner.map(transform)
      val refTransformation = scannerOrientationTransforms[ref]

      val totalTransform: Transform<Point3D> = { refTransformation(transform(it)) }

      scannerRef.forEach { beaconRef ->
        transformedScanner.forEach { possibleBeacon ->
          val possibleScannerPosition = diff(beaconRef, possibleBeacon)

          val projectiveCommonBeacons =
              scannerRef.count { diff(it, possibleScannerPosition) in transformedScanner }
          if (projectiveCommonBeacons >= 12) {
            scannerPositions.set(
                compared,
                sum(scannerPositions[ref], refTransformation(possibleScannerPosition))
            )
            scannerOrientationTransforms.set(compared, totalTransform)
            return
          }
        }
      }
    }
  }

  fun listAllScannersOverlappingWith(index: Int) {
    for (i in 0 until scanners.size) {
      if (i == index) continue
      if (i != 0 && scannerPositions[i] == Triple(0, 0, 0)) {
        checkScannerPositionAndTransform(index, i)
        if (scannerPositions[i] != Triple(0, 0, 0)) {
          println("Scanner #$i detected at ${scannerPositions[i]}")
          listAllScannersOverlappingWith(i)
        }
      }
    }
  }

  fun getNumberOfBeacons(): Int {
    val allBeacons =
        scanners.flatMapIndexedTo(mutableSetOf()) { index, elt ->
          elt.map { sum(scannerPositions[index], scannerOrientationTransforms[index](it)) }
        }

    return allBeacons.size
  }

  fun findLargestDistanceBetweenTwoScanners(): Int {
    return scannerPositions.maxOf { a -> scannerPositions.maxOf { b -> norm1(diff(b, a)) } }
  }
}

fun main() {
  val scenario = Day19Scenario()
  scenario.listAllScannersOverlappingWith(0)
  println("Exercice 1")
  println(scenario.getNumberOfBeacons())
  println("Exercice 2")
  println(scenario.findLargestDistanceBetweenTwoScanners())
}
