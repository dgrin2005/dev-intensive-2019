package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User (
    var id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    var lastVisit: Date? = Date(),
    var isOnline: Boolean = false) {

    constructor(id: String, firstName: String?, lastName: String? ) : this (
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null
    )

    constructor(id: String) : this (
        id = id,
        firstName = "John",
        lastName = "Doe"
    )

    constructor() : this (
        id = "0"
    )

    private constructor(builder: Builder) : this() {
        id = builder.id
        firstName = builder.firstName
        lastName = builder.lastName
        avatar = builder.avatar
        rating = builder.rating
        respect = builder.respect
        lastVisit = builder.lastVisit
        isOnline = builder.isOnline
    }

    companion object Factory {
        private var lastId = -1

        fun makeUser(fullName: String?): User {
            lastId++
            val (firstName, lastName) = Utils.parseFullName(fullName)
            return User(id = "$lastId", firstName = firstName, lastName = lastName)
        }
    }

    class Builder {
        var id: String = "0"
        var firstName: String? = null
        var lastName: String? = null
        var avatar: String? = null
        var rating: Int = 0
        var respect: Int = 0
        var lastVisit: Date? = Date()
        var isOnline: Boolean = false

        fun id(id: String = "0") = apply { this.id = id }

        fun firstName(firstName: String? = null) = apply { this.firstName = firstName }

        fun lastName(lastName: String? = null) = apply { this.lastName = lastName }

        fun avatar(avatar: String? = null) = apply { this.avatar = avatar }

        fun rating(rating: Int = 0) = apply { this.rating = rating }

        fun respect(respect: Int = 0) = apply { this.respect = respect }

        fun lastVisit(lastVisit: Date? = Date()) = apply { this.lastVisit = lastVisit }

        fun isOnline(isOnline: Boolean = false) = apply { this.isOnline = isOnline }

        fun build() = User(this)

    }
}