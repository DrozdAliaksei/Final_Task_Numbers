package com.example.finaltask.data.db

import android.database.sqlite.SQLiteDatabase
import com.example.finaltask.di.App
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils

class DatabaseHelper : OrmLiteSqliteOpenHelper(App.instance, "numbers.db", null, 1) {
    override fun onCreate(database: SQLiteDatabase?, connectionSource: ConnectionSource?) {
        TableUtils.createTableIfNotExists(connectionSource, NumberTable::class.java)
    }

    override fun onUpgrade(
        database: SQLiteDatabase?,
        connectionSource: ConnectionSource?,
        oldVersion: Int,
        newVersion: Int
    ) {
        TableUtils.dropTable<NumberTable, Any>(connectionSource, NumberTable::class.java, true)
        onCreate(database, connectionSource)
    }

}

