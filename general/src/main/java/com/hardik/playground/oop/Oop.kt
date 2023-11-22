package com.hardik.playground.oop

fun main() {
    val base: Base = Derive()
    base.foo()
    val derive: Derive = Base()
    derive.foo()
}

open class Base {
    fun foo() {
        println("Base")
    }
}

class Derive : Base() {
    override fun foo() {
        println("Derive")
    }
}
