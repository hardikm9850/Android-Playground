package com.hardik.playground.data

data class SomeDataClass(val string: String) {
    var integer: Int = 0
    constructor(number: Int) : this(string = "hello")
}

fun main() {
    val data = SomeDataClass("asv")
    val anotherData = SomeDataClass(1)
    data.integer = 1
}