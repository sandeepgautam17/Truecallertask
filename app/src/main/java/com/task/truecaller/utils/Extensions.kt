package com.task.truecaller.utils

// Extension function to find the 15th character
fun String.find15thCharacter(): String? {
    return if (this.length >= 15) this[14].toString() else null
}

// Extension function to find every 15th character
fun String.findEvery15thCharacter(): String {
    return this.indices.filter { it % 15 == 14 }.map { this[it] }.joinToString(" ")
}

// Extension function to count word frequency and return JSON
fun String.requestWordCounter(): String {
    val words = this.split(Regex("\\s+")).filter { it.isNotEmpty() }.map { it.lowercase() }
    val content = StringBuilder()
    words.groupingBy { it }.eachCount().forEach { (key, value) ->
        content.append("$key : $value \n")
    }
    return content.toString()
}
