package com.hardik.kotlin_playground.enums // ktlint-disable package-name

enum class Bit {
    ZERO {
        override fun foo() {
        }
    },
    ONE {
        override fun foo() {
        }
    },
    TWO {
        override fun foo() {
        }
    }, ;

    abstract fun foo()
}
