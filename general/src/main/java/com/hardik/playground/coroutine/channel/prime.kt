package com.hardik.playground.coroutine.channel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() = runBlocking{
    var cur = numbersFrom(2)
    repeat(10) {
        delay(1000)
        val prime = cur.receive()
        println("is number prime? $prime")
        cur = filter(cur, prime)
        println()
    }
    coroutineContext.cancelChildren()
}

fun CoroutineScope.numbersFrom(start: Int) = produce<Int> {
    var x = start
    while (true) {
        send(x++)
        println("sending element $x")
    } // infinite stream of integers from start
}

fun CoroutineScope.filter(numbers: ReceiveChannel<Int>, prime: Int) = produce<Int> {
    println("filter :: prime is $prime")
    for (x in numbers) if (x % prime != 0) {
        send(x)
        println("sending element from filter $x, for prime $prime")
    }
}