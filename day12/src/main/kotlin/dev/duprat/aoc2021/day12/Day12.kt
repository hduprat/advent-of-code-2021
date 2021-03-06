/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package dev.duprat.aoc2021.day12

import dev.duprat.aoc2021.utils.*

class Day12Scenario : Scenario {
    val caveSystem: Map<String, Set<String>>
    val smallCaves: Set<String>
    val largeCaves: Set<String>

    constructor(path: String = "/input.txt") : super(path) {
        caveSystem =
                lines.fold(mutableMapOf()) { map, line ->
                    val caves = line.split("-")
                    map.put(caves[0], unionSet(map.getOrPut(caves[0]) { setOf() }, caves[1]))
                    map.put(caves[1], unionSet(map.getOrPut(caves[1]) { setOf() }, caves[0]))
                    map
                }
        smallCaves =
                lines.fold(setOf()) { set, line ->
                    set union line.split("-").filter { it.lowercase() == it }
                }
        largeCaves =
                lines.fold(setOf()) { set, line ->
                    set union line.split("-").filter { it.uppercase() == it }
                }
    }

    fun findPathsToEnd(
            startNode: String = "start",
            currentPath: String = "",
            depth: Int = 1,
            hasSmallCaveBeenRevisited: Boolean = false,
    ): List<String?> {
        val possiblePaths = caveSystem.get(startNode)
        val newPath = if (currentPath.length > 0) "$currentPath,$startNode" else startNode
        if (possiblePaths == null) return listOf()
        else
                return possiblePaths
                        .mapNotNull {
                            when {
                                it == "start" && it in currentPath -> null
                                it == "end" -> listOf(newPath + ",end")
                                it in smallCaves &&
                                        currentPath.split(",").count { cave -> cave == it } ==
                                                depth -> null
                                it in smallCaves &&
                                        it in currentPath &&
                                        hasSmallCaveBeenRevisited -> null
                                it in smallCaves &&
                                        it in currentPath &&
                                        !hasSmallCaveBeenRevisited ->
                                        findPathsToEnd(it, newPath, depth, true)
                                else ->
                                        findPathsToEnd(
                                                it,
                                                newPath,
                                                depth,
                                                hasSmallCaveBeenRevisited
                                        )
                            }
                        }
                        .flatten()
    }

    fun countPaths(maxDepth: Int = 1): Int {
        return findPathsToEnd(depth = maxDepth).size
    }
}

fun main() {
    val scenario = Day12Scenario()
    println("Exercice 1")
    println(scenario.countPaths())
    println("Exercice 2")
    println(scenario.countPaths(2))
}
