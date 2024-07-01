package com.example.list_of_contacts

import android.content.ContentValues
import android.provider.BaseColumns

fun addContact(dbHelper: ContactDbHelper, name: String, phone: String) {
    val db = dbHelper.writableDatabase
    val values = ContentValues().apply {
        put(ContactContract.ContactEntry.COLUMN_NAME_NAME, name)
        put(ContactContract.ContactEntry.COLUMN_NAME_PHONE, phone)
    }
    db.insert(ContactContract.ContactEntry.TABLE_NAME, null,values)
    db.close()
}

fun getAllContacts(dbHelper: ContactDbHelper) : List<Contact> {
    val db = dbHelper.readableDatabase
    val projection = arrayOf(BaseColumns._ID, ContactContract.ContactEntry.COLUMN_NAME_NAME, ContactContract.ContactEntry.COLUMN_NAME_PHONE)
    val cursor = db.query(
        ContactContract.ContactEntry.TABLE_NAME,null,null,null,null,null,null
    )
    val contacts = mutableListOf<Contact>()
    with(cursor) {
        while (moveToNext()) {
            val id = getLong(getColumnIndexOrThrow(BaseColumns._ID))
            val name = getString(getColumnIndexOrThrow(ContactContract.ContactEntry.COLUMN_NAME_NAME))
            val phone = getString(getColumnIndexOrThrow(ContactContract.ContactEntry.COLUMN_NAME_PHONE))
            contacts.add(Contact(id,name, phone))
        }
    }
    cursor.close()
    db.close()
    return contacts
}

fun deleteContact(dbHelper: ContactDbHelper, id: Long) {
    val db = dbHelper.writableDatabase
    db.delete(ContactContract.ContactEntry.TABLE_NAME, "${BaseColumns._ID} = ?", arrayOf(id.toString()))
    db.close()
}