package com.hardik.design_pattern.structural

/**
 * What?
 * The Adapter design pattern is a structural pattern that allows objects with incompatible interfaces to work together.
 * It acts as a bridge === between two incompatible interfaces by providing a class (the Adapter) that translates calls
 * to one interface into calls to another interface that the client code expects.
 *
 * Scenario:
 * Imagine you have an e-commerce application that needs to handle different payment methods like credit cards, PayPal, and mobile wallets.
 * Each payment method has its own interface and implementation, but your application needs to work with a common Payment interface.
 */

// Target Interface
interface Payment {
    fun pay(amount: Double)
}


// Adaptee 1: Credit Card
class CreditCard(private val name: String, private val cardNumber: String, private val cvv: String, private val expiryDate: String) {
    fun makePayment(amount: Double) {
        // Process credit card payment
        println("$amount paid with credit card $cardNumber")
    }
}

// Adaptee 2: PayPal
class PayPal(private val email: String, private val password: String) {
    fun makePayment(amount: Double) {
        // Process PayPal payment
        println("$amount paid with PayPal account $email")
    }
}

// Adapter 1: Credit Card Adapter
class CreditCardAdapter(private val creditCard: CreditCard) : Payment {
    override fun pay(amount: Double) {
        creditCard.makePayment(amount)
    }
}

// Adapter 2: PayPal Adapter
class PayPalAdapter(private val payPal: PayPal) : Payment {
    override fun pay(amount: Double) {
        payPal.makePayment(amount)
    }
}

// Client code
fun main() {
    val creditCard = CreditCard("John Doe", "000000000000", "123", "12/25")
    val payPal = PayPal("john@example.com", "******")

    val creditCardAdapter = CreditCardAdapter(creditCard)
    val payPalAdapter = PayPalAdapter(payPal)

    processPayment(creditCardAdapter, 100.0)
    processPayment(payPalAdapter, 50.0)
}

fun processPayment(payment: Payment, amount: Double) {
    payment.pay(amount)
}