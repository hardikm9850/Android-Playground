package com.hardik.design_pattern.creational

/**
 * Simple Factory
 *
 * Simple factory simply generates an instance for client without exposing any instantiation logic to the client
 */

fun main() {
    val chair = WoodenFactory.createProduct(WoodenFactory.ProductType.CHAIR)
    val table = WoodenFactory.createProduct(WoodenFactory.ProductType.TABLE)

    println(chair.getDescription())
    println(table.getDescription())
}

// Interface for wooden products
interface WoodenProduct {
    fun getDescription(): String
}

// Concrete implementation of WoodenProduct: Chair
class Chair : WoodenProduct {
    override fun getDescription(): String {
        return "This is a wooden chair."
    }
}

// Concrete implementation of WoodenProduct: Table
class Table : WoodenProduct {
    override fun getDescription(): String {
        return "This is a wooden table."
    }
}

// Factory for creating wooden products
object WoodenFactory {
    enum class ProductType {
        CHAIR,
        TABLE
    }

    fun createProduct(type: ProductType): WoodenProduct {
        return when (type) {
            ProductType.CHAIR -> Chair()
            ProductType.TABLE -> Table()
        }
    }
}