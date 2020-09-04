package com.example.finaltask.data.db

import com.example.finaltask.data.local.model.Number
import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable(tableName = "numbers")
data class NumberTable(

    @DatabaseField(generatedId = true)
    val id: Int? = null,
    @DatabaseField
    val text: String = "",
    @DatabaseField
    val found: Boolean = false,
    @DatabaseField
    val number: Int = 0,
    @DatabaseField
    val type: String = "",
    @DatabaseField
    val date: String? = "",
    @DatabaseField
    val year: String? = ""
) {
    fun toNumber(): Number {
        return Number(
            text = this.text,
            found = this.found,
            number = this.number,
            type = this.type,
            date = this.date,
            year = this.year
        )
    }
}
