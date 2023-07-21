package com.hardik.dsa.tree

import java.util.LinkedList
import kotlin.math.max

fun main() {
    val node = Node(1)
    node.left = Node(2)
    node.right = Node(3)
    node.left?.left = Node(4)
    node.left?.right = Node(5)
    node.right?.right = Node(8)
    node.right?.right?.left = Node(6)
    node.right?.right?.right = Node(7)
    println("width is ${width(node)}")
}

/*   Constructed Binary tree is:
                1
              /   \
            2      3
          /  \      \
         4    5      8
                   /   \
                  6     7    */

fun width(node: Node): Int {
    var width = 0
    val queue = LinkedList<Node>()
    queue.push(node)

    while (queue.isEmpty().not()) {
        var currentWidth = queue.size
        width = max(currentWidth, width)

        while (currentWidth-- > 0) {
            val currentNode = queue.removeFirst()
            if (currentNode.left != null) {
                queue.push(currentNode.left)
            }
            if (currentNode.right != null) {
                queue.push(currentNode.right)
            }
        }
    }
    return width
}


data class Node(val data: Int, var left: Node? = null, var right: Node? = null)