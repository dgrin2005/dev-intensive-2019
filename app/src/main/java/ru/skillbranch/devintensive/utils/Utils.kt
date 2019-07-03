package ru.skillbranch.devintensive.utils


object Utils {

    fun parseFullName(fullName: String?):Pair<String?, String?> {
        if (fullName.isNullOrBlank()) {
            return Pair(null, null);
        }
        else {
            val parts: List<String>? = fullName.trim().split(" ")
            return Pair(parts?.getOrNull(0), parts?.getOrNull(1))
        }
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        if (firstName.isNullOrBlank()) {
            if (lastName.isNullOrBlank()) {
                return null
            } else {
                return lastName.toUpperCase().substring(0, 1)
            }
        } else {
            if (lastName.isNullOrBlank()) {
                return firstName.toUpperCase().substring(0, 1)
            } else {
                return firstName.toUpperCase().substring(0, 1) + lastName.toUpperCase().substring(0, 1)
            }
        }
    }

    fun transliteration(payload: String, divider: String = " "): String {
        var payloadTranslit = ""
        val parts: List<String> = payload.trim().split(" ")
        for (i in 0 until parts.size) {
            val part = parts[i].trim()
            if (!part.isBlank()) {
                for(j in 0 until part.length){
                    payloadTranslit += translitCharacter(part[j])
                }
                payloadTranslit += divider
            }
        }
        if (parts.isNotEmpty()) {
            payloadTranslit = payloadTranslit.substring(0, payloadTranslit.length - 1)
        }
        return payloadTranslit
    }

    private fun translitCharacter(ch: Char): String {
        return when (ch) {
            'а' -> "a"
            'б' -> "b"
            'в' -> "v"
            'г' -> "g"
            'д' -> "d"
            'е' -> "e"
            'ё' -> "e"
            'ж' -> "zh"
            'з' -> "z"
            'и' -> "i"
            'й' -> "i"
            'к' -> "k"
            'л' -> "l"
            'м' -> "m"
            'н' -> "n"
            'о' -> "o"
            'п' -> "p"
            'р' -> "r"
            'с' -> "s"
            'т' -> "t"
            'у' -> "u"
            'ф' -> "f"
            'х' -> "h"
            'ц' -> "c"
            'ч' -> "ch"
            'ш' -> "sh"
            'щ' -> "sh'"
            'ъ' -> ""
            'ы' -> "i"
            'ь' -> ""
            'э' -> "e"
            'ю' -> "yu"
            'я' -> "ya"
            'А' -> "A"
            'Б' -> "B"
            'В' -> "V"
            'Г' -> "G"
            'Д' -> "D"
            'Е' -> "E"
            'Ё' -> "E"
            'Ж' -> "Zh"
            'З' -> "Z"
            'И' -> "I"
            'Й' -> "I"
            'К' -> "K"
            'Л' -> "L"
            'М' -> "M"
            'Н' -> "N"
            'О' -> "O"
            'П' -> "P"
            'Р' -> "R"
            'С' -> "S"
            'Т' -> "T"
            'У' -> "U"
            'Ф' -> "F"
            'Х' -> "H"
            'Ц' -> "C"
            'Ч' -> "Ch"
            'Ш' -> "Sh"
            'Щ' -> "Sh'"
            'Ъ' -> ""
            'Ы' -> "I"
            'Ь' -> ""
            'Э' -> "E"
            'Ю' -> "Yu"
            'Я' -> "Ya"
            else -> "" + ch
        }
    }
}