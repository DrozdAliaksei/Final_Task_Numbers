package com.example.contacts.db

import android.database.sqlite.SQLiteDatabase
import com.example.contacts.App
import com.example.contacts.db.dao.Contact
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils

object DatabaseHelper : OrmLiteSqliteOpenHelper(App.instance, "test.db", null, 1) {

    override fun onCreate(database: SQLiteDatabase?, connectionSource: ConnectionSource?) {
        TableUtils.createTableIfNotExists(connectionSource, Contact::class.java)
    }

    override fun onUpgrade(database: SQLiteDatabase?, connectionSource: ConnectionSource?, oldVersion: Int, newVersion: Int) {
        TableUtils.dropTable<Contact, Any>(connectionSource, Contact::class.java, true)
        onCreate(database, connectionSource)
    }

}