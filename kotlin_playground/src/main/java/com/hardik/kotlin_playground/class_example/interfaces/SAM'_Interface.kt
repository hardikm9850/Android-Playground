package com.hardik.kotlin_playground.class_example.interfaces

// An interface with only one abstract method is called a functional interface, or a Single Abstract Method
fun interface IntPredicate {
    fun accept(i: Int): Boolean
}
val isEvent = IntPredicate { it % 2 == 0 }

fun main() {
}