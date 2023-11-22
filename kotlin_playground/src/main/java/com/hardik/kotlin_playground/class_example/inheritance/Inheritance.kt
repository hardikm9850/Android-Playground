package com.hardik.kotlin_playground.class_example.inheritance

open class Base {
    open fun foo() {
        println("I am base")
    }
}

class Derive : Base() {
    override fun foo() {
        val any = Any()
    }
}

/**
 * Overriding rules
 * In Kotlin, implementation inheritance is regulated by the following rule:
 * if a class inherits multiple implementations of the same member from its immediate superclasses,
 * it must override this member and provide its own implementation (perhaps, using one of the inherited ones).
 */
open class Rectangle {
    open fun draw() { /* ... */ }
}

interface Polygon {
    fun draw() { /* ... */ } // interface members are 'open' by default
}

class Square() : Rectangle(), Polygon {
    // The compiler requires draw() to be overridden:
    override fun draw() {
        super<Rectangle>.draw() // call to Rectangle.draw()
        super<Polygon>.draw() // call to Polygon.draw()
    }
}

fun main() {

}
