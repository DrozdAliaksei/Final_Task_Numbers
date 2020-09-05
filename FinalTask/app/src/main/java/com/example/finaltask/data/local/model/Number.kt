package com.example.finaltask.data.local.model

import com.example.finaltask.data.db.NumberTable

data class Number(
    val text: String = "",
    val found: Boolean = false,
    val number: Double? = null,
    val type: String = "",
    val date: String? = "",
    val year: String? = ""
) {
    fun name(): String {
        return if (!year.isNullOrBlank() && !date.isNullOrBlank()) "$number ,$date ,$year"
        else if (!year.isNullOrBlank()) "$number in $year"
        else if (!date.isNullOrBlank()) "$number, $date"
        else if (number == null) ""
        else "$number"
    }

    fun toNumberTable(): NumberTable {
        return NumberTable(
            text = this.text,
            found = this.found,
            number = this.number,
            type = this.type,
            date = this.date,
            year = this.year
        )
    }
}
