package com.hardik.dsa.tree.array

val array = intArrayOf(1, 2, 2, 1, 0, 0, 0, 1, 1, 0)

fun main() {
}

// Sort an array of 0s, 1s and 2s
fun sort_0_1_2() {
    val map = array.groupBy { it }.toSortedMap()
    val list = mutableListOf<Int>()
}
