package dev.duprat.aoc2021.day18

var counter = 0

class Node {
  var left: Node? = null
  var right: Node? = null
  var parent: Node? = null

  val id: String
  var _value: String

  constructor(value: String, leftChild: Node? = null, rightChild: Node? = null) {
    _value = value
    id = "n$counter"
    counter++
    updateChildren(leftChild, rightChild)
  }

  fun isLeaf(): Boolean {
    return left == null && right == null
  }

  fun updateChildren(leftChild: Node?, rightChild: Node?) {
    if (leftChild != null) leftChild.parent = this
    left = leftChild
    if (rightChild != null) rightChild.parent = this
    right = rightChild
  }

  val depth: Int
    get() {
      if (parent == null) return 0
      else {
        val parentDepth = parent?.depth
        return 1 + (parentDepth ?: -1)
      }
    }

  fun getValue(): String {
    if (isLeaf()) return _value
    if (left == null || right == null) throw Error("Wrong node with one sub-node null")
    return "[${left!!.getValue()},${right!!.getValue()}]"
  }

  fun getImmediateLeft(): Node? {
    if (parent == null) return null
    if (this == parent!!.right) return parent!!.left
    val parentLeft = parent!!.getImmediateLeft()
    if (parentLeft == null) return null
    if (parentLeft.isLeaf()) return parentLeft
    return parentLeft.right
  }

  fun getImmediateRight(): Node? {
    if (parent == null) return null
    if (this == parent!!.left) {
      return parent!!.right
    }
    val parentRight = parent!!.getImmediateRight()
    if (parentRight == null) return null
    if (parentRight.isLeaf()) return parentRight
    return parentRight.left
  }

  fun getMagnitude(): Long {
    if (isLeaf()) return getValue().toLong()
    return 3 * left!!.getMagnitude() + 2 * right!!.getMagnitude()
  }
}
