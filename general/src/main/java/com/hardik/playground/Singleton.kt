package com.hardik.playground

class Singleton private constructor(val data: String) {
    init {
        println("foo")
    }
}

fun main() {
    // Singleton.singletonInstance
}
