package com.example.list_of_contacts

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ContactDbHelper(context: Context) : SQLiteOpenHelper(context,DATABASE_NAME, null,DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "Contacts.db"
        const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(ContactContract.SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(ContactContract.SQL_DELETE_ENTRIES)
        onCreate(db)
    }

}
