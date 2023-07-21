package com.hardik.design_pattern.creational

/**
 * Simple Factory
 *
 * Simple factory simply generates an instance for client without exposing any instantiation logic to the client
 */

fun main() {
    WoodenFactory.makeDoor(200f, 200f)
}

interface Door {
    fun getWidth(): Float
    fun getHeight(): Float
}

class WoodenDoor(private val width: Float, private val height: Float) : Door {
    override fun getWidth(): Float {
        return width
    }

    override fun getHeight(): Float {
        return height
    }
}

class WoodenFactory {
    companion object {
        fun makeDoor(width: Float, height: Float): WoodenDoor {
            return WoodenDoor(width, height)
        }
    }

}
