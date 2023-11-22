package com.hardik.kotlin_playground.exception

/**
 * Checked exceptions
 * Represent errors outside the control of the program. For example, the constructor of FileInputStream throws FileNotFoundException if the input file does not exist.
 * This exceptions are verified at the compile time
 *
 * Kotlin does not have checked exceptions
 * Instead we are forced to check the method in interest to see if it throws any exception and handle accordingly [https://stackoverflow.com/a/58646575]
 */
fun main() {
    throwExceptionMethod()
}

/**
 * Nothing states that the method never returns (always throws an exception).
 */
fun throwExceptionMethod(): Nothing {
    throw IllegalArgumentException()
}
