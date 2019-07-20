package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User(
    /** первичный конструктор */
    val id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    val lastVisit: Date? = null, // время последнего визита, тип Date, значение по умолчанию null, поэтому знак "?"
    val isOnline: Boolean = false

) {
    /** дополниетельное поле, которое мы объявили (переменная)*/
//    var introBit: String

    /** вторичный конструктор */
    constructor(id: String, firstName: String?, lastName: String?) : this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null
    )

    /** еще один вторичный конструктор */
    constructor(id: String) : this(
        id, "John", "Doe"
    )

    /** блок инициализации*/
    init {

    }

    /** создадим companion object (объект, который будет идти с нашим объектом User), будет обладать некторыми статическими функциями, т.е. к этим функиям мы сможем обратиться из
     * любой части кода
     */

    /** шаблон проектирования Factory позволяет нам создавать объекты для объектов, и она удобна в тех случаях, если перед созданием объекта нам необходимо сделать какие-то
     * преоборазования(например, отформатировать...)
     */
    companion object Factory {
        private var lastId: Int = -1
        fun makeUser(fullName: String?): User {
            lastId++

            val (firstName, lastName) = Utils.parseFullName(fullName)
            return User(id = "$lastId", firstName = firstName, lastName = lastName)
        }
    }

    class Builder {
        var id: String = ""
        var firstName: String? = null
        var lastName: String? = null
        var avatar: String? = null
        var rating: Int = 0
        var respect: Int = 0
        var lastVisit: Date? = Date()
        var isOnline: Boolean = false

        fun id(id: String) = apply { this.id = id }

        fun firstName(firstName: String?) = apply { this.firstName = firstName }

        fun lastName(lastName: String?) = apply { this.lastName = lastName }

        fun avatar(avatar: String?) = apply { this.avatar = avatar }

        fun rating(rating: Int) = apply { this.rating = rating }

        fun respect(respect: Int) = apply { this.respect = respect }

        fun lastVisit(lastVisit: Date?) = apply { this.lastVisit = lastVisit }

        fun isOnline(isOnline: Boolean) = apply { this.isOnline = isOnline }

        fun build() = User(id, firstName, lastName, avatar, rating, respect, lastVisit, isOnline)
    }
}
