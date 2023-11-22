package com.hardik.kotlin_playground.class_example.sealed

sealed class GameConsole(val version: String?) {
    data class PlayStation(val psVersion: String): GameConsole(psVersion)
    data class Xbox(val xboxVersion: String): GameConsole(xboxVersion)
}

fun main() {
    val xboxGameConsole : GameConsole = GameConsole.Xbox("PS4")
    val playStationGameConsole : GameConsole = GameConsole.PlayStation("360")
}