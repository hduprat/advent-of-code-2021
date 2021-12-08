package dev.duprat.aoc2021.utils

fun <T> unionSet(set: Set<T>, vararg elements: T): Set<T> {
  return set.union(elements.toSet())
}
