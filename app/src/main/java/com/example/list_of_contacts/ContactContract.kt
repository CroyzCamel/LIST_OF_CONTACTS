package com.example.list_of_contacts

import android.provider.BaseColumns

object ContactContract {
    object ContactEntry : BaseColumns {
        const val TABLE_NAME = "contacts"
        const val COLUMN_NAME_NAME = "name"
        const val COLUMN_NAME_PHONE = "phone"
    }

    const val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${ContactEntry.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${ContactEntry.COLUMN_NAME_PHONE} TEXT," +
                "${ContactEntry.COLUMN_NAME_PHONE} TEXT)"

    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${ContactEntry.TABLE_NAME}"
}