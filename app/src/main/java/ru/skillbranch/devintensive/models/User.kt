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

    companion object Factory {
        private var lastId = -1

        fun makeUser(fullName: String?): User {
            lastId++
            val (firstName, lastName) = Utils.parseFullName(fullName)
            return User(id = "$lastId", firstName = firstName, lastName = lastName)
        }
    }

    inner class Builder
    {
        fun id(id: String): Builder {
            this@User.id = id
            return this
        }

        fun firstName(firstName: String?): Builder {
            this@User.firstName = firstName
            return this
        }

        fun lastName(lastName: String?): Builder {
            this@User.lastName = lastName
            return this
        }

        fun avatar(avatar: String): Builder {
            this@User.avatar = avatar
            return this
        }

        fun rating(rating: Int = 0): Builder {
            this@User.rating = rating
            return this
        }

        fun respect(respect: Int = 0): Builder {
            this@User.respect = respect
            return this
        }

        fun lastVisit(lastVisit: Date? = Date()): Builder {
            this@User.lastVisit = lastVisit
            return this
        }

        fun isOnline(isOnline: Boolean = false): Builder {
            this@User.isOnline = isOnline
            return this
        }

        fun build(): User {
            return this@User
        }

    }
}