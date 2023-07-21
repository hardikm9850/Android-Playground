package com.hardik.playground.keywords

class LazyExample{
    private val lazyData by lazy { Data("") }
    lateinit var lateInitData: Data

}

data class Data(val string: String) {
}