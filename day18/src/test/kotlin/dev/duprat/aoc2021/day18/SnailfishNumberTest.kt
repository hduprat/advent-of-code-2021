package dev.duprat.aoc2021.day18

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SnailfishNumberTest {
  @Test
  fun testSimplest() {
    val number = SnailfishNumber("[1,2]")
    assertEquals(1, number.nodeMap.size)
    assertEquals("[1,2]", number.baseNode.getValue())
    assertEquals("1", number.baseNode.left?.getValue())
    assertEquals("2", number.baseNode.right?.getValue())
  }

  fun getExplodedValue(originValue: String): String {
    return run {
      val n = SnailfishNumber(originValue)
      n.explode()
      n.baseNode.getValue()
    }
  }

  @Test
  fun testExplode() {
    assertEquals("[[[[0,9],2],3],4]", getExplodedValue("[[[[[9,8],1],2],3],4]"))
    assertEquals("[7,[6,[5,[7,0]]]]", getExplodedValue("[7,[6,[5,[4,[3,2]]]]]"))
    assertEquals("[[6,[5,[7,0]]],3]", getExplodedValue("[[6,[5,[4,[3,2]]]],1]"))
    assertEquals(
        "[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]",
        getExplodedValue("[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]")
    )
  }

  fun getSplitValue(originValue: String): String {
    return run {
      val n = SnailfishNumber(originValue)
      n.split()
      n.baseNode.getValue()
    }
  }

  @Test
  fun testSplit() {
    assertEquals(
        "[[[[0,7],4],[[7,8],[0,13]]],[1,1]]",
        getSplitValue("[[[[0,7],4],[15,[0,13]]],[1,1]]")
    )
  }

  @Test
  fun testReduce() {
    val n = SnailfishNumber("[[[[[4,3],4],4],[7,[[8,4],9]]],[1,1]]")
    n.reduce()
    assertEquals("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]", n.baseNode.getValue())
  }

  @Test
  fun testAdd() {
    val n = SnailfishNumber("[1,1]")
    n.add("[2,2]")
    n.add("[3,3]")
    n.add("[4,4]")
    assertEquals("[[[[1,1],[2,2]],[3,3]],[4,4]]", n.baseNode.getValue())

    n.add("[5,5]")
    assertEquals("[[[[3,0],[5,3]],[4,4]],[5,5]]", n.baseNode.getValue())

    n.add("[6,6]")
    assertEquals("[[[[5,0],[7,4]],[5,5]],[6,6]]", n.baseNode.getValue())
  }

  @Test
  fun testMagnitude() {
    assertEquals(21, SnailfishNumber("[[1,9]]").magnitude)
    assertEquals(29, SnailfishNumber("[[9,1]]").magnitude)
    assertEquals(129, SnailfishNumber("[[9,1],[1,9]]").magnitude)
    assertEquals(
        3488,
        SnailfishNumber("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]").magnitude
    )
  }
}
