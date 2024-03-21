package com.hardik.design_pattern.creational

/**
 *  Abstract Factory pattern is to provide an interface for creating families of related objects, allowing clients to create
 *  objects without specifying their concrete classes, thereby promoting loose coupling between the client and the created objects.
 *
 *  This pattern is useful when you need to ensure that the created objects are compatible and belong to a specific family or group.
 *
 *  The main idea behind the Abstract Factory pattern is to provide an interface for creating families of related objects,
 *  allowing clients to create objects without specifying their concrete classes, thereby promoting loose coupling
 *  between the client and the created objects.
 */

class AbstractFactory

// Declares an interface for a type of product. These are the products created by the factories.
interface Button {
    fun click()
}

interface Keyboard {
    fun shouldHavePrintScreen(): Boolean
}

// Implements the abstract product interface to create specific product instances.
class WindowsButton : Button {
    override fun click() {
        println("Windows button clicked")
    }
}

class MacButton : Button {
    override fun click() {
        println("Mac button clicked")
    }
}

class WindowsKeyBoard : Keyboard {
    override fun shouldHavePrintScreen() = true
}

class MacKeyboard : Keyboard {
    override fun shouldHavePrintScreen() = false
}

// Declares a set of methods for creating abstract products. This could be an interface or an abstract class.
interface InputFactory {
    fun createButton(): Button
    fun createKeyboard(): Keyboard
}

// Implements the abstract factory interface to create concrete products.
// Each concrete factory corresponds to a specific variant or family of products.
class WindowFactory : InputFactory {
    override fun createButton(): Button {
        return WindowsButton()
    }

    override fun createKeyboard(): Keyboard {
        return WindowsKeyBoard()
    }
}

class MacFactory : InputFactory {
    override fun createButton(): Button {
        return MacButton()
    }

    override fun createKeyboard(): Keyboard {
        return MacKeyboard()
    }
}

// Client : Uses the abstract factory and product interfaces to work with the concrete product instances without having to know their concrete classes.
class Application(factory: InputFactory) {
    private val button: Button = factory.createButton()
    private val keyboard: Keyboard = factory.createKeyboard()

    fun boot() {
        button.click()
        keyboard.shouldHavePrintScreen()
    }
}

fun main() {
    val windowsApp = Application(WindowFactory())
    windowsApp.boot()

    val macApp = Application(MacFactory())
    macApp.boot()
}

