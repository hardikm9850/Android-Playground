package com.hardik.playground

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import org.junit.Assert
import org.junit.Test
import java.lang.StringBuilder

class Excercise {
    @Test
    fun addition_isCorrect() {
        val result = reverseString("abcd")
        Assert.assertEquals(result,"dcba")
    }


    fun reverseString(str: String) : String{
        val builder = StringBuilder()
        for (i in str.length - 1 downTo 0) {
            builder.append(str[i])
        }
        return builder.toString()
    }



    val channel = Channel<String>()


    fun createCoroutineChannel() {
        CoroutineScope(Job()).launch {
            channel.consumeEach {
                println("consume $it")
            }
            for (i in 5 downTo 0) {
                channel.send(i.toString())
            }
        }
    }
}