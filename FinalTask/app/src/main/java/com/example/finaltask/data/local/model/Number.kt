package com.example.finaltask.data.local.model

data class Number(
    val text: String? = "",
    val found: Boolean? = false,
    val number: Int? = 0,
    val type: String? = "",
    val date: String? = "",
    val year: String? = ""
) {
    fun name(): String {
        return if (!year.isNullOrBlank() && !date.isNullOrBlank()) "$number in $date of $year"
        else if (!year.isNullOrBlank()) "$number in $year"
        else if (!date.isNullOrBlank()) "$number at $date"
        else "$number"
    }
}
