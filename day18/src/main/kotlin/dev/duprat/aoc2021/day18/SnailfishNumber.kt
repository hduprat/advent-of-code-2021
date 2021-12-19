package dev.duprat.aoc2021.day18

class SnailfishParseError(str: String) : Error("Could not parse $str") {}

val PairRegex = Regex("""\[(\w+),(\w+)\]""")

class SnailfishNumber {
  val numberMap: MutableMap<String, Pair<String, String>> = mutableMapOf()
  val parentMap: MutableMap<String, String> = mutableMapOf()
  val nodeMap: MutableMap<String, Node> = mutableMapOf()
  lateinit var baseNode: Node

  constructor(baseStr: String) {
    var str = baseStr
    while (str.length > 1) {
      val result = PairRegex.find(str)
      if (result == null) break
      val (left, right) = result.destructured
      val node =
          Node(
              result.groupValues[0],
              if (nodeMap.containsKey(left)) nodeMap[left] else Node(left),
              if (nodeMap.containsKey(right)) nodeMap[right] else Node(right),
          )
      baseNode = node
      nodeMap.put(node.id, node)
      str = PairRegex.replaceFirst(str, node.id)
    }
  }

  fun getLeftmostExplodingNode(startNode: Node = this.baseNode): Node? {
    if (startNode.isLeaf()) return null
    if (startNode.depth == 4) return startNode
    val explodingNode = getLeftmostExplodingNode(startNode.left!!)
    if (explodingNode == null) return getLeftmostExplodingNode(startNode.right!!)
    else return explodingNode
  }

  fun explode(): Boolean {
    val exploNode = getLeftmostExplodingNode()
    if (exploNode == null) return false
    val leftValue = exploNode.left?._value?.toInt() ?: 0
    val immediateLeft = exploNode.getImmediateLeft()
    if (immediateLeft != null) {
      if (immediateLeft.isLeaf()) immediateLeft._value = "${immediateLeft._value.toInt()+leftValue}"
      else {
        val realLeft = immediateLeft.right
        if (realLeft == null) throw Error("Wrong right node")
        realLeft._value = "${realLeft._value.toInt()+leftValue}"
      }
    }

    val rightValue = exploNode.right?._value?.toInt() ?: 0
    val immediateRight = exploNode.getImmediateRight()
    if (immediateRight != null) {
      if (immediateRight.isLeaf())
          immediateRight._value = "${immediateRight.getValue().toInt()+rightValue}"
      else {
        val realRight = immediateRight.left
        if (realRight == null) throw Error("Wrong left node")
        immediateRight.left!!._value = "${realRight.getValue().toInt()+rightValue}"
      }
    }
    exploNode.updateChildren(null, null)
    exploNode._value = "0"
    return true
  }

  fun getLeftmostSplittingLeaf(startNode: Node = this.baseNode): Node? {
    if (startNode.isLeaf()) {
      if (startNode.getValue().toInt() > 9) return startNode
      return null
    }
    var splittingLeaf = getLeftmostSplittingLeaf(startNode.left!!)
    if (splittingLeaf == null) return getLeftmostSplittingLeaf(startNode.right!!)
    else return splittingLeaf
  }

  fun split(): Boolean {
    val splitLeaf = getLeftmostSplittingLeaf()
    if (splitLeaf == null) return false
    val valueToSplit = splitLeaf.getValue().toInt()
    val isEven = valueToSplit % 2 == 0
    val leftVal = valueToSplit / 2
    val rightVal = valueToSplit / 2 + if (isEven) 0 else 1
    splitLeaf.updateChildren(Node("$leftVal"), Node("$rightVal"))
    return true
  }

  fun reduce() {
    var stop = false
    while (!stop) {
      if (!explode()) {
        if (!split()) {
          stop = true
        }
      }
    }
  }

  fun add(b: String) {
    val bNumber = SnailfishNumber(b)
    val newBaseNode = Node("", baseNode, bNumber.baseNode)
    baseNode = newBaseNode
    reduce()
  }

  val magnitude: Long
    get() {
      return baseNode.getMagnitude()
    }
}
