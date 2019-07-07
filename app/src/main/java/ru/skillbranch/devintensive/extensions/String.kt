package ru.skillbranch.devintensive.extensions

fun String.truncate(value: Int = 16): String {
    val fill = "..."
    val substring = this.substring(0, value).trimEnd()
    if (substring == this.trimEnd()) {
        return substring
    } else {
        return substring + fill
    }
}

fun String.stripHtml():String?{
    var newString = ""
    var i = 0
    while (i < this.length) {
        when (this[i]) {
            '<' -> {
                var newString2 = ""
                var j = true
                while (j) {
                    j = i < this.length
                    if (j) {
                        if (this[i] != '>') {
                            newString2 += this[i]
                        } else {
                            j = false
                        }
                    }
                    i++
                }
                if (i > this.length) {
                    newString += "<" + newString2.substring(1, newString2.length).stripHtml()
                }
            }
            '&' -> {
                var newString2 = ""
                var j = true
                while (j) {
                    j = i < this.length
                    if (j) {
                        if (this[i] != ';') {
                            newString2 += this[i]
                        } else {
                            j = false
                        }
                    }
                    i++
                }
                if (i > this.length) {
                    newString += "&" + newString2.substring(1, newString2.length).stripHtml()
                }
            }
            ' ' -> {
                var j = true
                while(j) {
                    j = i < this.length
                    if (j) {
                        if (this[i] == ' ') {
                            i++
                        } else {
                            j = false
                        }
                    }
                }
                newString += ' '
            }
            '\"' -> {
                i++
            }
            '\'' -> {
                i++
            }
            else -> {
                newString += this[i]
                i++
            }
        }

    }
    return newString
}