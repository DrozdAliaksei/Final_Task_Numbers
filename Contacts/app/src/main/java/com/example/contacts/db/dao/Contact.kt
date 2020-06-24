package com.example.contacts.db.dao

import com.example.contacts.db.DatabaseHelper
import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable(tableName = "contacts")
data class Contact(

    @DatabaseField(generatedId = true)
    var id: Int? = null,

    @DatabaseField
    var name: String = "",

    @DatabaseField
    var number: String = ""
)

class ContactDao {

    private val dao = DatabaseHelper.getDao(Contact::class.java)


    fun add(contact: Contact) = dao.createOrUpdate(contact)

    fun update(contact: Contact) = dao.update(contact)

    fun delete(contact: Contact) = dao.delete(contact)

    fun queryForAll() = dao.queryForAll()

    fun removeAll() {
        for (table in queryForAll()) {
            dao.delete(table)
        }
    }

}