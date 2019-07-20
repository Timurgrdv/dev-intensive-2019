package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName:String?):Pair<String?, String?>{

        /** здесь метод split() разделяет строку, используя пробел*/
        val parts : List<String>? = fullName?.split(" ")    // опять же ставим "?", т.к. fullName может быть Null

        /** метод getOrNull используется для того, чтобы если в нашей коллекции нет объекта с индексом '0', то он вернёт нам Null*/
        val firstName = parts?.getOrNull(0)?.ifEmpty { null }
        val lastName = parts?.getOrNull(1)?.ifEmpty { null }

        return firstName to lastName   // Pair - это пара, дата-класс
    }

    fun transliteration(payload: String, divider : String = " "): String {
        val map = fillTranslitMap()
        val builder = StringBuilder()

        for (char in payload.trim())
            builder.append(getTranslitarationChar(char, map))

        return builder.toString().replace(" ", divider)
    }

    private fun getTranslitarationChar(char : Char, map : HashMap<Char, String>) : String {
        val translit = map[char.toLowerCase()] ?: char.toString()

        return if (char.isUpperCase() && translit.isNotEmpty())
            translit.capitalize()
        else translit
    }

    private fun fillTranslitMap() : HashMap<Char, String> {
        val map = hashMapOf<Char, String>()
        map['а'] = "a"
        map['б'] = "b"
        map['в'] = "v"
        map['г'] = "g"
        map['д'] = "d"
        map['е'] = "e"
        map['ё'] = "e"
        map['ж'] = "zh"
        map['з'] = "z"
        map['и'] = "i"
        map['й'] = "i"
        map['к'] = "k"
        map['л'] = "l"
        map['м'] = "m"
        map['н'] = "n"
        map['о'] = "o"
        map['п'] = "p"
        map['р'] = "r"
        map['с'] = "s"
        map['т'] = "t"
        map['у'] = "u"
        map['ф'] = "f"
        map['х'] = "h"
        map['ц'] = "c"
        map['ч'] = "ch"
        map['ш'] = "sh"
        map['щ'] = "sh'"
        map['ъ'] = ""
        map['ы'] = "i"
        map['ь'] = ""
        map['э'] = "e"
        map['ю'] = "yu"
        map['я'] = "ya"

        return map
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val name = firstName.orEmpty().trim().getOrNull(0)?.toUpperCase()  // возвращает строку если она не 'null' или пустую строку в противном случае
        val surname = lastName.orEmpty().trim().getOrNull(0)?.toUpperCase()

        val firstInit = name?.toString() ?: ""
        val secondInit = surname?.toString() ?: ""

        return "$firstInit$secondInit".ifEmpty { null }
    }

}