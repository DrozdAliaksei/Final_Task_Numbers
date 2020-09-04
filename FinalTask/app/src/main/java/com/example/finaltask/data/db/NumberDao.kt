package com.example.finaltask.data.db

class NumberDao(val dao: DatabaseHelper) {

    val helper = dao.getDao(NumberTable::class.java)

    fun add(number: NumberTable) =
        helper.createOrUpdate(number)

    fun queryForAll() = helper.queryForAll()

    fun typedList(type: String) = helper.queryBuilder().where().eq("type", type).query()

    fun removeAll() {
        for (table in queryForAll()) {
            helper.delete(table)
        }
    }
}
