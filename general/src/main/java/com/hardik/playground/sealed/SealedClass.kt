package com.hardik.playground.sealed

sealed class SealedClass {
    object ObjectSealed : SealedClass()
    data class DataSealed(val data: String) : SealedClass()
    class ClassSealed : SealedClass()
}
