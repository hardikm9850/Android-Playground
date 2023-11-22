package com.hardik.dsa.tree.array

private val array1 = intArrayOf(1, 2, 2, 3, 3, 3, 4, 5, 5)
private val array2 = intArrayOf(4, 5)
private val array3 = intArrayOf(5, 6, 7, 8)

fun main() {
    findDuplicate()
    findDuplicateFromThreeArray()
}

// Given an array, determine if there are repeated elements. If an element is repeated more than 3 times, return those elements
fun findDuplicate() {
    val filteredList = array1.groupBy { it }.filter { it.value.size == 3 }

    println(filteredList)
}

// Three integer arrays are given with duplicate numbers. Find the common elements among three arrays
fun findDuplicateFromThreeArray() {
    val duplicateElement =
        array1.intersect(array2.toList().toSet()).intersect(array3.toList().toSet())
    println("duplicate element $duplicateElement")
}
