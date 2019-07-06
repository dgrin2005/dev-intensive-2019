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