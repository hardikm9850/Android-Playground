package com.hardik.design_pattern.creational

class User private constructor(
    private val firstName: String,
    private val lastName: String,
    private val age: Int,
    private val email: String
) {
    data class Builder(
        var firstName: String = "",
        var lastName: String = "",
        var age: Int = 0,
        var email: String = ""
    ) {
        fun firstName(firstName: String) = apply { this.firstName = firstName }
        fun lastName(lastName: String) = apply { this.lastName = lastName }
        fun age(age: Int) = apply { this.age = age }
        fun email(email: String) = apply { this.email = email }

        fun build() = User(firstName, lastName, age, email)
    }

    override fun toString(): String {
        return "User(firstName='$firstName', lastName='$lastName', age=$age, email='$email')"
    }
}

fun main() {
    val user = User.Builder()
        .firstName("Hardik")
        .lastName("Mehta")
        .age(30)
        .email("hardik@yahoo.co.in")
        .build()

    println(user)
}

